import sbt._
import Keys._

object Build extends Build {

  val VERSION = "0.1.0"
  val projectName = "archetype"

  lazy val basicSettings = Seq(
    name := projectName,
    organization := "org.naasir",
    version := VERSION,
    scalaVersion := "2.11.8",
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature",
      "-unchecked",
      "-Xfatal-warnings",
      "-Xfuture",
      "-Xlint",
      "-Yno-adapted-args",
      "-Ywarn-dead-code",
      // "-Ywarn-numeric-widen",
      "-Ywarn-unused",
      "-Ywarn-unused-import"
    )
  )

  lazy val resolverSettings = Seq(
    resolvers ++= Seq(
      Resolver.sonatypeRepo("public"),
      Resolver.typesafeRepo("releases")
    )
  )

  lazy val testSettings = Seq(
    testOptions in Test := Seq(
      Tests.Argument(TestFrameworks.ScalaTest, "-oD"), // console reporter w/ durations
      Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports") // junit xml reporter
    )
  )

  lazy val dependencySettings = Seq(
    libraryDependencies ++= Seq(
      "commons-codec"  % "commons-codec" % "1.10",
      "org.typelevel" %% "cats"          % "0.7.2",
      // -- Testing --
      "org.scalactic" %% "scalactic" % "3.0.0",
      "org.scalatest" %% "scalatest" % "3.0.0" % "test"
    )
  )

  lazy val root = Project("root", file("."))
    .settings(basicSettings: _*)
    .settings(testSettings: _*)
    .settings(resolverSettings: _*)
    .settings(dependencySettings: _*)
}
