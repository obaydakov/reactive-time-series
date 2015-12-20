name := "kafka-event-consumer"

organization := "com.mintbeans"

version := "0.1"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= Seq(
  "com.softwaremill.reactivekafka" %% "reactive-kafka-core"              % "0.8.3",
  "org.slf4j"                      %  "slf4j-simple"                     % "1.7.13",
  "junit"                          %  "junit"                            % "4.12"  % "test",
  "org.scalatest"                  %% "scalatest"                        % "2.2.5" % "test",
  "com.typesafe.akka"              %% "akka-stream-testkit-experimental" % "1.0"   % "test"
)
