package com.jiffey.slick.additions

import com.github.nscala_time.time.Imports._

import java.net.InetAddress
import java.sql.Timestamp

import scala.language.implicitConversions
import scala.slick.driver.BasicProfile
import scala.slick.lifted._
import scala.slick.session.{PositionedParameters, PositionedResult}

object TypeMapper {

  class InetJdbcType extends BaseTypeMapper[InetAddress] with TypeMapperDelegate[InetAddress] {

    override val sqlTypeName = "inet"

    def apply(p: BasicProfile) = this

    override def valueToSQLLiteral(value: InetAddress) = "'%s'".format(value.getHostAddress)

    def nextValue(r: PositionedResult): InetAddress = InetAddress.getByName(r.nextString())

    def sqlType: Int = java.sql.Types.OTHER

    def updateValue(v: InetAddress, r: PositionedResult): Unit = r.updateObject(v.getHostAddress)

    def setValue(v: InetAddress, p: PositionedParameters): Unit = p.setObject(v.getHostAddress, sqlType)

    def zero: InetAddress = InetAddress.getByName("0.0.0.0/0")

    def setOption(v: Option[InetAddress], p: PositionedParameters): Unit =
      p.setObjectOption(v map (_.getHostAddress), sqlType)

  }

  object Implicits {

    implicit val inetJdbcType = new InetJdbcType

    implicit val dateTimeJdbcType = MappedTypeMapper.base[DateTime, Timestamp](
      { d: DateTime => new Timestamp(d.getMillis) },
      { t: Timestamp => new DateTime(t.getTime, DateTimeZone.UTC) }
    )

  }

}
