import sbt._
import Keys._
import play.Project._

object SlickAdditionsBuild extends Build {

  lazy val slickAdditions =
    Project(id = "slick-additions", base = file("."), settings = Project.defaultSettings).settings(
      scalaVersion := "2.10.2",
      version := "0.1",
      organization := "com.jiffey",
      resolvers += Resolver.sonatypeRepo("releases"),
      libraryDependencies += "play" %% "play" % "2.1.2",
      libraryDependencies += jdbc,
      libraryDependencies += "com.typesafe.slick" %% "slick" % "1.0.1",
      libraryDependencies += "com.github.tminglei" % "slick-pg_2.10.1" % "0.1.2",
      libraryDependencies += "com.github.scaldi" %% "scaldi" % "0.2",
      libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "0.4.2"
    )

}
