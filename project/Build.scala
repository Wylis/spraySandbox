import sbt._
import Keys._

object BuildSettings {
  val buildOrganization = "org.wylis"
  val buildScalaVersion = "2.11.6"

  val akkaVersion = "2.3.11"
  val scalatestVersion = "2.2.+"
  val sprayVersion = "1.3.1"

  val buildSettings = Defaults.defaultSettings  ++ Seq (
    organization := buildOrganization,
    scalaVersion := buildScalaVersion,
    resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
  )
}

object Resolvers {
  val typesafeSnapshot  = "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/"
  val sprayRepo = "Spray repository" at "http://repo.spray.io"
}

object Dependencies {
  lazy val jodatime    = "joda-time" % "joda-time" % "2.+"
  lazy val jodaconvert    = "org.joda" % "joda-convert" % "1.+"
  //lazy val scalaz       = "org.scalaz" %% "scalaz-core" % "7.1.+"

  lazy val akka_actor  = "com.typesafe.akka" %% "akka-actor" % BuildSettings.akkaVersion

  lazy val spray_routing = "io.spray" %% "spray-routing" % BuildSettings.sprayVersion
  lazy val spray_testkit =  "io.spray" %% "spray-testkit" % BuildSettings.sprayVersion % "test"
  lazy val spray_httpx = "io.spray" %% "spray-httpx" % BuildSettings.sprayVersion
  lazy val spray_can = "io.spray" %% "spray-can" % BuildSettings.sprayVersion

  lazy val typesafe_config = "com.typesafe" % "config" % "1.2.+"

  lazy val scalatest = "org.scalatest" %% "scalatest" % BuildSettings.scalatestVersion % "test"
  lazy val akka_testkit = "com.typesafe.akka" %% "akka-testkit" % BuildSettings.akkaVersion % "test"

}

trait CommonBuild {
  import Dependencies._
  import Resolvers._
  def commonProject(name: String, baseFile: java.io.File) =
    Project(id = name, base = baseFile, settings = BuildSettings.buildSettings).settings(
      libraryDependencies += akka_actor,
      libraryDependencies += spray_routing,
      libraryDependencies += spray_can,
      libraryDependencies += jodatime,
      libraryDependencies += jodaconvert,
      libraryDependencies += typesafe_config,
      libraryDependencies += scalatest
    )
}

object SpraySandboxBuild extends Build with CommonBuild {
  lazy val root = commonProject("spray-sandbox", file("."))
}