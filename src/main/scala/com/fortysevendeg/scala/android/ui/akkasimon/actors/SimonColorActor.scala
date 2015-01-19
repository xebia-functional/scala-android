package com.fortysevendeg.scala.android.ui.akkasimon.actors

import akka.actor.{ActorLogging, Props}

import com.fortysevendeg.scala.android.ui.akkasimon.fragments.SimonColorFragment
import macroid.Ui
import macroid.akkafragments.FragmentActor

object SimonColorActor {

  case class LightOn(game: List[Option[SimonColorFragment]])

  def props = Props(new SimonColorActor)
}

class SimonColorActor extends FragmentActor[SimonColorFragment] with ActorLogging {
  import SimonColorActor._
  import macroid.akkafragments.FragmentActor._

  def receive = receiveUi andThen {
    case LightOn(Nil) =>
    case LightOn(Some(head) :: tail) =>
      withUi(f => f.receive ~~ Ui(head.actor.get ! LightOn(tail)))
    case AttachUi(_) =>
    case DetachUi =>
  }
}