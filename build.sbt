name := """test-form"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val myProject = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "net.java.dev.activeobjects" % "activeobjects" % "0.8.2",
   "org.flywaydb" %% "flyway-play" % "2.0.1",
    "org.webjars" % "jquery" % "2.1.1",
   "org.webjars" % "bootstrap" % "3.3.1"
  )

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := true