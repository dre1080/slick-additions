package com.jiffey.slick.additions

import com.github.nscala_time.time.Imports._

import com.jiffey.slick.additions.TypeMapper.Implicits._

import java.util.UUID

import scala.slick.driver.BasicDriver.Table

abstract class RepoTable[M <: Model[M]](val db: DatabaseComponent, name: String) extends Table[M](db.entityName(name)) {

  // standardizing the following columns for all entities
  def id = column[UUID]("id", O.PrimaryKey, O.Nullable)
  def createdAt = column[DateTime]("created_at", O.NotNull)
  def updatedAt = column[DateTime]("updated_at", O.NotNull)

  def forInsert: db.Driver.KeysInsertInvoker[M, UUID]

  def timestamp = DateTime.now

}
