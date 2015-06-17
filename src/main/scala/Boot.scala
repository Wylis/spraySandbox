import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import httpServer.SandboxServer.Start
import httpServer.{SandboxServer, HttpServer}

object Boot extends App {
  val conf = ConfigFactory.load()
  implicit val system = ActorSystem("my-sandbox-system")
  val server = system.actorOf(SandboxServer.props(conf.getConfig("http-server")))

  server ! Start

}
