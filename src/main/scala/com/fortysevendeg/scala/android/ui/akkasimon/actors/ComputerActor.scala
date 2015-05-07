package com.fortysevendeg.scala.android.ui.akkasimon.actors

import akka.actor.{ActorLogging, ActorSelection, Props}
import com.fortysevendeg.scala.android.ui.akkasimon.AkkaSimonActivity
import com.fortysevendeg.scala.android.ui.akkasimon.actors.ColorActor.LightOn
import com.fortysevendeg.scala.android.ui.akkasimon.fragments.ComputerFragment
import macroid.akka.FragmentActor

import scala.collection.mutable.ArrayBuffer

class ComputerActor extends FragmentActor[ComputerFragment] with ActorLogging {

  import com.fortysevendeg.scala.android.ui.akkasimon.actors.ComputerActor._
  import macroid.akka.FragmentActor._

  var gameList: List[RoundItemActorColor] = List.empty

  val receivedFromUser: ArrayBuffer[ClickedUserColor] = ArrayBuffer.empty

  def receive = receiveUi andThen {
    case NewGame =>
      withUi(f => f.newGame)
    case GameOver =>
      val rounds = gameList.length
      gameList = List.empty
      withUi(f => f.getActivity.asInstanceOf[AkkaSimonActivity].gameOver(rounds))
    case ResetRound(round) =>
      addRound(round)
      withUi(f => f.getActivity.asInstanceOf[AkkaSimonActivity].resetRound())
    case NextRound(round) =>
      addRound(round)
      withUi(f => f.getActivity.asInstanceOf[AkkaSimonActivity].nextRound())
    case userEvent: ClickedUserColor =>
      receivedFromUser += userEvent
      withUi(f => f.checkGame(gameList, receivedFromUser.toList map (_.color)))
    case AttachUi(_) =>
    case DetachUi =>
  }

  def addRound(round: RoundItemActorColor) = {
    gameList = gameList :+ round
    receivedFromUser.clear()
    gameList.head.actor ! LightOn(gameList.tail)
  }

}

object ComputerActor {

  case object NewGame

  case object GameOver

  case class ResetRound(round: RoundItemActorColor)

  case class NextRound(round: RoundItemActorColor)

  case class RoundItemActorColor(actor: ActorSelection, color: Int)

  case class ClickedUserColor(color: Int)

  def props = Props(new ComputerActor)
}