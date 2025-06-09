name := "oracle-switcher"

version := "0.1.0-SNAPSHOT"

scalaVersion := "3.3.1"

enablePlugins(ScalaJSPlugin)

scalaJSUseMainModuleInitializer := true

scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) }

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.8.0"
