organization := "org.scalablytyped"
name := "react-icons"
version := "2.2-824a3e"
scalaVersion := "3.2.0"
enablePlugins(ScalaJSPlugin)
libraryDependencies ++= Seq(
  "com.olvind" %%% "scalablytyped-runtime" % "2.4.2",
  "org.scalablytyped" %%% "react" % "0.0-unknown-5cf9a0",
  "org.scalablytyped" %%% "react-icon-base" % "2.1-af6fd6",
  "org.scalablytyped" %%% "std" % "0.0-unknown-a639e4")
publishArtifact in packageDoc := false
scalacOptions ++= List("-encoding", "utf-8", "-feature", "-language:implicitConversions", "-language:higherKinds", "-language:existentials", "-no-indent", "-source:future")
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
