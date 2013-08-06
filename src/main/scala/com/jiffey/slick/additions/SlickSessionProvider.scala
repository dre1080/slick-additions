package com.jiffey.slick.additions

import scala.concurrent.ExecutionContext
import scala.slick.session.{Database => SlickDatabase, Session}

trait SlickSessionProvider {

  val executionContext: ExecutionContext
  def createReadOnlySession(handle: SlickDatabase): Session
  def createReadWriteSession(handle: SlickDatabase): Session

}
