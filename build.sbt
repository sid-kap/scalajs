enablePlugins(ScalaJSPlugin)

name := "Scala.js Tutorial"

scalaVersion := "2.11.5" // or any other Scala version >= 2.10.2
//libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.0"
//libraryDependencies += "com.greencatsoft" %%% "scalajs-d3" % "0.2-SNAPSHOT"
//libraryDependencies += ProjectRef(file("../clones/scalajs-d3"), "scalajs-d3")

lazy val scalajs_d3 = ProjectRef(file("../clones/scalajs-d3"), "root") //project in file("../clones/scalajs-d3")

lazy val main = project in file(".") dependsOn scalajs_d3
