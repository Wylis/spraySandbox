package httpServer

import akka.actor._
import common.Problem

class HttpServer extends Actor {
  import httpServer.HttpServer._

  override def receive: Receive = initialize

  def initialize: Receive = {
    case Start => ???
    case Stop => ???
  }
}

object HttpServer {
  sealed trait HttpServerMsg
  sealed trait HttpServerMsgRsp

  case object Start extends HttpServerMsg
  case object Started extends HttpServerMsgRsp
  final case class StartFailed(problem: Problem) extends HttpServerMsgRsp

  case object Stop extends HttpServerMsg
  case object Stopped extends HttpServerMsgRsp
  case class StopFailed(problem: Problem) extends HttpServerMsgRsp
}