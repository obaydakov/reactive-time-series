import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.stream.testkit.javadsl._
import akka.testkit.TestKit

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EventConsumerSpec extends TestKit(ActorSystem("EventConsumerSpec")) with WordSpecLike with BeforeAndAfterAll {

  sealed trait TestContext extends EventConsumer {
    implicit val materializer = ActorMaterializer()
  }

  override def afterAll() {
    system.shutdown()
  }

  "EventConsumer" should {
    "convert an observation stream to a memory stream and keep the valid format" in new TestContext  {
      override val timeWindowLength = 3
      val source = Source(Vector("100 1", "101 2", "102 3", "103 4", "104 5", "200 6"))

      memorySource(source)
        .map(_.toString)
        .runWith(TestSink.probe[String](system))
        .requestNext("100 1 1 1 1 1")
        .requestNext("101 2 2 3 1 2")
        .requestNext("102 3 3 6 1 3")
        .requestNext("103 4 3 9 2 4")
        .requestNext("104 5 3 12 3 5")
        .requestNext("200 6 1 6 6 6")
        .expectComplete()
    }
  }

}