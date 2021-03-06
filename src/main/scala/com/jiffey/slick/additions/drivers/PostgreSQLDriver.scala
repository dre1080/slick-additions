package com.jiffey.slick.additions.drivers

import com.github.tminglei.slickpg._

import scala.slick.driver.PostgresDriver

trait PostgreSQLDriver extends PostgresDriver
                                with PgArraySupport
                                with PgRangeSupport
                                with PgHStoreSupport
                                with PgSearchSupport
                                with PostGISSupport {

  override val Implicit = new ImplicitsPlus {}
  override val simple   = new SimpleQLPlus {}

  trait ImplicitsPlus extends Implicits
                        with ArrayImplicits
                        with RangeImplicits
                        with HStoreImplicits
                        with SearchImplicits
                        with PostGISImplicits

  trait SimpleQLPlus extends SimpleQL
                        with ImplicitsPlus
                        with SearchAssistants

}

object PostgreSQLDriver extends PostgreSQLDriver

