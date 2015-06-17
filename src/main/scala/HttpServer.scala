import akka.actor.Actor.Receive
import akka.actor._

class HttpServer extends Actor {
  override def receive: Receive = initialize

  def initialize: Receive = ???
}

object HttpServer