lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "isc.advent",
      scalaVersion := "2.13.12"
    )),
    name := "advent_23_scala"
  )

libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.9.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % Test

