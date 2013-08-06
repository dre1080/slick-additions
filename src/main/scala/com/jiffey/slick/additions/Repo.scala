package com.jiffey.slick.additions

import com.jiffey.slick.additions.session._

import java.util.UUID

trait Repo[M <: Model[M]] {

  def get(id: Id[M])(implicit session: ReadSession): M
  def getOption(id: Id[M])(implicit session: ReadSession): Option[M]
  def all()(implicit session: ReadSession): Seq[M]
  def save(model: M)(implicit session: ReadWriteSession): M
  def count(implicit session: ReadSession): Int
  def page(page: Int = 0, size: Int = 20)(implicit session: ReadSession): Seq[M]

}
