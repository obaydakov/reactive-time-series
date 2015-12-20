name := "kafka-event-producer"

organization := "com.mintbeans"

version := "0.1"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "0.8.2.2",
  "org.slf4j"        % "slf4j-simple"  % "1.7.13"
)
