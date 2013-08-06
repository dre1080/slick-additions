package com.jiffey.slick.additions

import scala.slick.driver.ExtendedDriver
import scala.slick.session.{Database => SlickDatabase}

trait DatabaseComponent {

  // the actual Slick driver (e.g. H2 or PostgreSQL)
  val Driver: ExtendedDriver

  // dialect specific to this driver for functions that Slick does not support
  val dialect: DatabaseDialect[_]

  // A database instance for which connections can be created.
  // Encapsulates either a DataSource or parameters for DriverManager.getConnection().
  val handle: SlickDatabase

  // PostgreSQL and H2 have different preferences on casing the table and column names.
  // H2 specifically prefers upper case
  def entityName(name: String): String = name

}
