package com.jiffey.slick.additions

import com.jiffey.slick.additions.session._

import java.sql.SQLException

import scala.concurrent.{Future, future}
import scala.language.implicitConversions
import scala.slick.session.Session
import scala.slick.util.Logging

import scaldi.{Injectable, Injector}

class Database(implicit inj: Injector) extends Logging with Injectable {

  val db = inject [DatabaseComponent]
  val sessionProvider = inject [SlickSessionProvider]

  val dialect: DatabaseDialect[_] = db.dialect

  implicit def roToSession(roSession: ReadOnlySession): Session = roSession.session
  implicit def rwToSession(rwSession: ReadWriteSession): Session = rwSession.session

  def readOnlyAsync[T](f: ReadOnlySession => T): Future[T] =
    future(readOnly(f))(sessionProvider.executionContext)

  def readWriteAsync[T](f: ReadWriteSession => T): Future[T] =
    future(readWrite(f))(sessionProvider.executionContext)

  def readWriteAsync[T](attempts: Int)(f: ReadWriteSession => T): Future[T] =
    future(readWrite(attempts)(f))(sessionProvider.executionContext)

  def readOnly[T](f: ReadOnlySession => T): T = {
    var s: Option[Session] = None
    val ro = new ReadOnlySession({
      s = Some(sessionProvider.createReadOnlySession(db.handle))
      s.get
    })
    try f(ro) finally s.foreach(_.close())
  }

  def readWrite[T](f: ReadWriteSession => T): T = {
    val s = sessionProvider.createReadWriteSession(db.handle)
    try {
      s.withTransaction {
        f(new ReadWriteSession(s))
      }
    } finally s.close()
  }

  def readWrite[T](attempts: Int)(f: ReadWriteSession => T): T = {
    1 to attempts - 1 foreach { attempt =>
      try {
        return readWrite(f)
      } catch {
        case t: SQLException =>
          val throwableName = t.getClass.getSimpleName
          logger.warn(s"Failed ($throwableName) readWrite transaction attempt $attempt of $attempts")
      }
    }
    readWrite(f)
  }

}
