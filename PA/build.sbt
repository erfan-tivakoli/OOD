name := """PA"""

version := "1.0-SNAPSHOT"

lazy val myProject = (project in file(".")).enablePlugins(PlayJava,  PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
//  jdbc,
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)
