package com.fortysevendeg.scala.android.ui.akkasimon.actors

import akka.actor.{ActorLogging, Props}
import com.fortysevendeg.scala.android.ui.akkasimon.actors.ComputerActor.RoundItemActorColor
import com.fortysevendeg.scala.android.ui.akkasimon.fragments.ColorFragment
import macroid.Ui
import macroid.akkafragments.FragmentActor

class ColorActor extends FragmentActor[ColorFragment] with ActorLogging {
  import com.fortysevendeg.scala.android.ui.akkasimon.actors.ColorActor._
  import macroid.akkafragments.FragmentActor._

  def receive = receiveUi andThen {
    case LightOn(Nil) =>
      withUi(f => f.receive)
    case LightOn(head :: tail) =>
      withUi(f => f.receive ~~ Ui(head.actor ! LightOn(tail)))
    case AttachUi(_) =>
    case DetachUi =>
  }
}

object ColorActor {

  case class LightOn(game: List[RoundItemActorColor])

  def props = Props(new ColorActor)
}