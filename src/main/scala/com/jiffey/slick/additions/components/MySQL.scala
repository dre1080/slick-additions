package com.jiffey.slick.additions.components

import com.jiffey.slick.additions.DatabaseComponent
import com.jiffey.slick.additions.dialects.MySQLDatabaseDialect

import scala.slick.driver.MySQLDriver
import scala.slick.session.{Database => SlickDatabase}

class MySQL(val handle: SlickDatabase) extends DatabaseComponent {

  val Driver = MySQLDriver
  val dialect = MySQLDatabaseDialect

}

object MySQL {

  val driverName = "com.mysql.jdbc.Driver"

}
