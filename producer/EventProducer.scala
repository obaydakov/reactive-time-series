import java.util.concurrent.Future
import java.util.concurrent.ThreadLocalRandom
import java.time._
import org.apache.kafka.clients.producer._

trait KafkaConfig {
  lazy val kafkaProperties = {
    val props = new java.util.Properties()
    props.put("client.id",         "EventProducer")
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer",    "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer",  "org.apache.kafka.common.serialization.StringSerializer")
    props
  }
}

trait KafkaMessages {
  lazy val messages = Iterator.continually({
    val time = Instant.now.toEpochMilli
    val measurement = ThreadLocalRandom.current.nextDouble(0.0, 100.0) 
         
    val key = "sensor0"
    val value = s"${time} ${measurement}"
    val topic = "events"
    val partition = 0
        
    new ProducerRecord[String, String](topic, partition, key, value)
  })
}

object EventProducer extends App with KafkaMessages with KafkaConfig {   
  lazy val producer = new KafkaProducer[String, String](kafkaProperties)
  try {
    for (msg <- messages.take(10)) {
      Thread.sleep(1000)
      producer.send(msg)
    }
  } finally {
    producer.close()
  }
}
