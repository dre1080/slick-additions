package com.jiffey.slick.additions

import java.util.UUID

/**
 * A default Id implementation.
 *
 * @param value The id value
 * @tparam M    The model type the id belongs to.
 */
case class Id[M <: Model[M]](value: UUID) extends Pk[UUID] {

  override def toString = value.toString

}
