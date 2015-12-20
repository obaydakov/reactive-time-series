# Reactive Time Series

Time series computation based on [Kafka](http://kafka.apache.org/), [Akka Streams](http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0/scala.html) and [Reactive Kafka](https://github.com/softwaremill/reactive-kafka). Key components:

* An event producer for sample data generation 
* An event consumer for actual time series calculation
* A basic test case with [Akka Streams TestKit](http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0/scala/stream-testkit.html)
