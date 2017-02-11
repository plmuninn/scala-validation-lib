lazy val commonSettings = Seq(
  name := "validator-lib",
  organization := "pl.onewebpro",
  version := "1.0",
  scalaVersion := "2.12.1"
)

val catsVersion = "0.9.0"
val circeVersion = "0.7.0"
val scalaTestVersion = "3.0.1"

val commonLibs = Seq (
  "org.typelevel" %% "cats"           % catsVersion,
  "org.scalactic" %% "scalactic"      % scalaTestVersion,
  "org.scalatest" %% "scalatest"      % scalaTestVersion  % "test"
)

val coreLibs =  Seq()

val akkaLibs = Seq (

)

val circeLibs = Seq (
  "io.circe"      %% "circe-core"     % circeVersion,
  "io.circe"      %% "circe-generic"  % circeVersion,
  "io.circe"      %% "circe-parser"   % circeVersion
)


lazy val core = (project in file("core"))
  .settings(commonSettings, libraryDependencies ++= coreLibs ++ commonLibs)
  .enablePlugins(spray.boilerplate.BoilerplatePlugin)

lazy val akka = (project in file("akka"))
  .settings(commonSettings, libraryDependencies ++= akkaLibs ++ commonLibs)
  .aggregate(core)

lazy val circe = (project in file("circe"))
  .settings(commonSettings, libraryDependencies ++= circeLibs ++ commonLibs)
  .aggregate(core)
