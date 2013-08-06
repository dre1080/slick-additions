package com.jiffey.slick.additions

import com.github.nscala_time.time.Imports._

import java.lang.reflect.Field
import java.util.UUID

import scala.language.reflectiveCalls

abstract class Model[T <: Model[T]] extends Cloneable {

  val id: Option[Id[T]]
  val createdAt: DateTime
  val updatedAt: DateTime

  protected lazy val cloned = this.clone()

  protected def getField(field: String): Field = getClass.getDeclaredField(field)

  protected def setField(field: String, value: Any) {
    val declaredField = getField(field)
    declaredField.setAccessible(true)
    declaredField.set(cloned, value)
  }

  def withId(newId: Option[Id[T]]): T = {
    setField("id", newId)
    cloned.asInstanceOf[T]
  }

  def withCreatedAt(newCreatedAt: DateTime): T = {
    setField("createdAt", newCreatedAt)
    cloned.asInstanceOf[T]
  }

  def withUpdatedAt(newUpdatedAt: DateTime): T = {
    setField("updatedAt", newUpdatedAt)
    cloned.asInstanceOf[T]
  }

  def withTimestamps(newTimestamp: DateTime): T = {
    setField("createdAt", newTimestamp)
    setField("updatedAt", newTimestamp)
    cloned.asInstanceOf[T]
  }

}
