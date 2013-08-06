package com.jiffey.slick.additions.session

import scala.slick.session.Session

class ReadOnlySession(roSession: => Session) extends ReadSession(roSession)
