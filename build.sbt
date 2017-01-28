name := "validator-lib"

version := "1.0"

scalaVersion := "2.12.1"

val catsVersion = "0.9.0"
val circeVersion = "0.7.0"
val shapelessVersion = "2.3.2"

libraryDependencies ++= Seq(

  "org.typelevel" %% "cats"           % catsVersion,
  "io.circe"      %% "circe-core"     % circeVersion,
  "io.circe"      %% "circe-generic"  % circeVersion,
  "io.circe"      %% "circe-parser"   % circeVersion,
  "com.chuusai"   %% "shapeless"      % shapelessVersion

)
