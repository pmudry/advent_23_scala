lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.13.12"
    )),
    name := "advent_23_scala"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % Test
