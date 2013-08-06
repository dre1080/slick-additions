package com.jiffey.slick.additions.session

import java.sql.{PreparedStatement, Connection}

import scala.collection.mutable
import scala.slick.session.{ResultSetHoldability, ResultSetConcurrency, ResultSetType, Session}

abstract class SessionWrapper(_session: => Session) extends Session {

  lazy val session = _session

  def conn: Connection = session.conn

  def metaData = session.metaData

  def capabilities = session.capabilities

  override def resultSetType = session.resultSetType

  override def resultSetConcurrency = session.resultSetConcurrency

  override def resultSetHoldability = session.resultSetHoldability

  def close() {
    throw new UnsupportedOperationException
  }

  def rollback() {
    session.rollback()
  }

  def withTransaction[T](f: => T): T = session.withTransaction(f)

  private val statementCache = new mutable.HashMap[String, PreparedStatement]

  def getPreparedStatement(statement: String): PreparedStatement =
    statementCache.getOrElseUpdate(statement, this.conn.prepareStatement(statement))

  override def forParameters(rsType: ResultSetType = resultSetType,
                             rsConcurrency: ResultSetConcurrency = resultSetConcurrency,
                             rsHoldability: ResultSetHoldability = resultSetHoldability) =
    throw new UnsupportedOperationException

}
