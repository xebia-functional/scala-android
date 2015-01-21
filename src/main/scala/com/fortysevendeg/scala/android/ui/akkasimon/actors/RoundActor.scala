package com.fortysevendeg.scala.android.ui.akkasimon.actors

import akka.actor.{ActorLogging, Props}
import com.fortysevendeg.scala.android.ui.akkasimon.fragments.RoundFragment
import macroid.akkafragments.FragmentActor

class RoundActor extends FragmentActor[RoundFragment] with ActorLogging {

  import com.fortysevendeg.scala.android.ui.akkasimon.actors.RoundActor._
  import macroid.akkafragments.FragmentActor._

  def receive = receiveUi andThen {
    case ResetRound =>
      withUi(f => f.resetRound ~ f.newRound)
    case NextRound =>
      withUi(f => f.incrementRound ~ f.newRound)
    case GameOver => withUi(f => f.youLoose)
    case AttachUi(_) =>
    case DetachUi =>
  }
}

object RoundActor {

  case object ResetRound

  case object NextRound

  case object GameOver

  def props = Props(new RoundActor)
}