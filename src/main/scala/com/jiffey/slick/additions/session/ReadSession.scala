package com.jiffey.slick.additions.session

import scala.slick.session.Session

abstract class ReadSession(roSession: => Session) extends SessionWrapper(roSession)
