name := "simplecli-scala"

version := "0.1"

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "mainargs" % "0.6.3",
  "org.scalatest"  %% "scalatest"  % "3.2.19"  % Test
)

logBuffered := false

Test / parallelExecution := false

coverageExcludedPackages := """.*\.simple\..*;.*\.common.*;.*\.Main;benchmark\..*"""

enablePlugins(JavaAppPackaging)
