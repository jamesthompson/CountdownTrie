name := "Countdown Trie"

version := "0.1a"

scalaVersion := "2.10.1"

javaHome := Some(new File("/Library/Java/JavaVirtualMachines/1.7.0.jdk/Contents/Home"))

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

mainClass in (Compile, run) := Some("countdown.Solver")

unmanagedJars in Compile += Attributed.blank(file("/Library/Java/JavaVirtualMachines/1.7.0.jdk/Contents/Home/jre/lib/jfxrt.jar"))

libraryDependencies += "org.scalaz" % "scalaz-core_2.10" % "7.0.0"

libraryDependencies += "org.specs2" %% "specs2" % "1.14" % "test"
  
resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")

retrieveManaged := true