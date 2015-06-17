package httpServer

import akka.actor._
import com.typesafe.config.Config
import spray.can.Http
import common.Problem

class SandboxServer(config: Config)(implicit val system: ActorSystem) extends Actor {
  import SandboxServer._

  private val host = config.getString("host")
  private val port = config.getInt("port")

  private val httpServer = system.actorOf(HttpServer.props(config))

  override def receive: Receive = initialize
  
  def initialize: Receive = {
    case Start => akka.io.IO(Http) ! Http.Bind(httpServer, interface = host, port = port)
  }

  def running: Receive = {
    case Start => sender ! StartFailed("Service is already running!")
    case Stop => ???
  }
}

object SandboxServer {
  def props(config: Config)(implicit system: ActorSystem): Props = {
    Props(new SandboxServer(config))
  }

  sealed trait HttpServerMsg
  sealed trait HttpServerMsgRsp

  case object Start extends HttpServerMsg
  case object Started extends HttpServerMsgRsp
  final case class StartFailed(problem: Problem) extends HttpServerMsgRsp

  case object Stop extends HttpServerMsg
  case object Stopped extends HttpServerMsgRsp
  case class StopFailed(problem: Problem) extends HttpServerMsgRsp
}