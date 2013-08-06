package com.jiffey.slick.additions.components

import com.jiffey.slick.additions.DatabaseComponent
import com.jiffey.slick.additions.dialects.H2DatabaseDialect

import scala.slick.driver.H2Driver
import scala.slick.session.{Database => SlickDatabase}

class H2(val handle: SlickDatabase) extends DatabaseComponent {

  val Driver = H2Driver
  val dialect = H2DatabaseDialect

  override def entityName(name: String): String = name.toUpperCase

}

object H2 {

  val driverName = "org.h2.Driver"

}
