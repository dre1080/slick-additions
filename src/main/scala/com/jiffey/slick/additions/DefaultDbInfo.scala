package com.jiffey.slick.additions

import play.api.Play
import play.api.db.DB
import play.api.Play.current

import scala.slick.session.{Database => SlickDatabase}

class DefaultDbInfo extends DbInfo {

  def database = SlickDatabase.forDataSource(DB.getDataSource("default"))
  def driverName = Play.configuration.getString("db.default.driver").get

}
