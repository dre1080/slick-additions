package com.jiffey.slick.additions.session

import scala.slick.session.Session

class ReadWriteSession(rwSession: Session) extends ReadSession(rwSession)
