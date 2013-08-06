package com.jiffey.slick.additions

import scala.slick.session.{Database => SlickDatabase}

trait DbInfo {

  def database: SlickDatabase
  def driverName: String

}
