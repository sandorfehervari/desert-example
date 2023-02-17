ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "desert-example"
  )

val AkkaVersion = "2.6.19"
val AkkaHttpVersion = "10.2.9"

libraryDependencies += "io.github.vigoo" %% "desert-core" % "0.3.0"
libraryDependencies += "io.github.vigoo" %% "desert-zio-schema" % "0.3.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
)