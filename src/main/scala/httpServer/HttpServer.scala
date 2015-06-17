package httpServer

import akka.actor._
import com.typesafe.config.Config
import spray.routing._
import spray.http.MediaTypes._

class HttpServer(config: Config)(implicit val system: ActorSystem)
  extends Actor
  with ActorLogging
  with HttpService {
  override def receive: Receive = runRoute(dummy)

  override implicit def actorRefFactory: ActorRefFactory = context

  private val dummy = path("dummy") {
    get {
      complete {
        <p>Well done dude! It's running</p>
      }
    }
  }
}


object HttpServer {
  def props(config: Config)(implicit system: ActorSystem): Props = {
    Props(new HttpServer(config))
  }
}
