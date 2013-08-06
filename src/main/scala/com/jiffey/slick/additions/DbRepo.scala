package com.jiffey.slick.additions

import com.jiffey.slick.additions.session._

import java.sql.SQLException
import java.util.UUID

import scala.language.postfixOps

import scaldi.{Injectable, Injector}

abstract class DbRepo[M <: Model[M]](implicit inj: Injector) extends Repo[M] with Injectable {

  val db = inject [DatabaseComponent]

  import db.Driver.simple._

  def table: RepoTable[M]

  def count(implicit session: ReadSession): Int = Query(table.length).first

  def get(id: UUID)(implicit session: ReadSession): M =
    (for(f <- table if f.id is id.bind) yield f).first

  def getOption(id: UUID)(implicit session: ReadSession): Option[M] =
    (for(f <- table if f.id is id.bind) yield f).firstOption

  def all()(implicit session: ReadSession): Seq[M] = table.map(t => t).list

  def page(page: Int = 0, size: Int = 20)(implicit session: ReadSession): Seq[M] = {
    val q = for(t <- table) yield t
    q.sortBy(_.createdAt desc).drop(page * size).take(size).list
  }

  def save(model: M)(implicit session: ReadWriteSession): M = try {
    val toUpdate = model.withUpdatedAt(table.timestamp)
    model.id match {
      case Some(id) => update(toUpdate)
      case None => toUpdate.withId(Some(insert(toUpdate)))
    }
  } catch {
    case t: SQLException => throw new SQLException(s"error persisting $model", t)
  }

  private def insert(model: M)(implicit session: ReadWriteSession): UUID =
    table.forInsert.insert(model)

  private def update(model: M)(implicit session: ReadWriteSession) = {
    val target = for(t <- table if t.id === model.id.get) yield t
    val count = target.update(model)
    assert(1 == count)
    model
  }

}
