package com.jiffey.slick.additions

import java.util.UUID

class Id[M <: Model[M]](val id: UUID) extends AnyVal
