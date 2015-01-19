package com.fortysevendeg.scala.android.ui.akkasimon

import akka.actor.{ActorLogging, Props}
import macroid.Ui
import macroid.akkafragments.FragmentActor

object SimonColorActor {

  case class LightOn(game: List[Option[SimonColorFragment]])

  def props = Props(new SimonColorActor)
}

class SimonColorActor extends FragmentActor[SimonColorFragment] with ActorLogging {

  import com.fortysevendeg.scala.android.ui.akkasimon.SimonColorActor._
  import macroid.akkafragments.FragmentActor._

  def receive = receiveUi andThen {
    case LightOn(Nil) =>
    case LightOn(Some(head) :: tail) =>
      println(s"Sending message from ${head.actor.get} with message ${LightOn(tail)}")
      withUi(f => f.receive ~~ Ui(head.actor.get ! LightOn(tail)))
      println(s"Pending tail => " + tail)
    case AttachUi(_) =>
    case DetachUi =>
  }
}