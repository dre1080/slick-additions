package com.jiffey.slick.additions.components

import com.jiffey.slick.additions.DatabaseComponent
import com.jiffey.slick.additions.dialects.PostgreSQLDatabaseDialect
import com.jiffey.slick.additions.drivers.PostgreSQLDriver

import scala.slick.session.{Database => SlickDatabase}

class PostgreSQL(val handle: SlickDatabase) extends DatabaseComponent  {

  val Driver = PostgreSQLDriver
  val dialect = PostgreSQLDatabaseDialect

}

object PostgreSQL {

  val driverName = "org.postgresql.Driver"

}
