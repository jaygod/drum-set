val akkaVersion = "2.3.9"
val sprayVersion = "1.3.1"

lazy val root = (project in file(".")).
  settings(
    name := "SuperTurboDrumKit",
    version := "1.0",
    scalaVersion := "2.11.7",
    mainClass in Compile := Some("DrumService")
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka"   %%  "akka-actor"    % akkaVersion,
  "io.spray"            %%  "spray-routing" % sprayVersion,
  "io.spray"            %%  "spray-client"  % sprayVersion,
  "io.spray"            %%  "spray-testkit" % sprayVersion
)
libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.11" % "1.0.2"
