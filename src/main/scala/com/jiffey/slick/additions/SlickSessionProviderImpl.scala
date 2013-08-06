package com.jiffey.slick.additions

import play.api.Play.current
import play.api.libs.concurrent.Akka

import scala.slick.session.{Database => SlickDatabase, Session, ResultSetConcurrency}

class SlickSessionProviderImpl extends SlickSessionProvider {

  val ContextId = "akka.actor.slick-context"
  val executionContext = Akka.system.dispatchers.lookup(ContextId)

  def createReadOnlySession(handle: SlickDatabase): Session = {
    handle.createSession().forParameters(rsConcurrency = ResultSetConcurrency.ReadOnly)
  }

  def createReadWriteSession(handle: SlickDatabase): Session = {
    handle.createSession().forParameters(rsConcurrency = ResultSetConcurrency.Updatable)
  }

}
