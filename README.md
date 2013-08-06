# Slick Additions

Additional slick classes/utils to help with database abstraction, dependency injection etc.

Uses [Scaldi](https://github.com/scaldi/scaldi).

Based on the following links:

* [Using Database Dialects within Play Framework](http://eng.42go.com/using-database-dialects-with-in-play-framework/)
* [Managing Scala Slick Database Drivers & Type-Safeing Session Concurrency](http://eng.42go.com/scala-slick-database-drivers-type-safing-session-concurrency/)

### Example Module

```scala
package modules

import com.jiffey.slick.additions._
import com.jiffey.slick.additions.components._

import play.api.Play
import play.api.Play.current

import scaldi.Module

class SlickModule(dbInfo: DbInfo) extends Module {

  val config = Play.configuration

  lazy val db = dbInfo.driverName match {
    case PostgreSQL.driverName => new PostgreSQL(dbInfo.database)
    case MySQL.driverName => new MySQL(dbInfo.database)
    case H2.driverName => new H2(dbInfo.database)
    case _ => throw config.reportError(
      dbInfo.driverName,
      s"Slick error : Unknown jdbc driver found in application.conf: [${dbInfo.driverName}]"
    )
  }

  bind [DatabaseComponent] to db
  bind [SlickSessionProvider] to new SlickSessionProviderImpl
  bind [Database] to new Database()

}
```
