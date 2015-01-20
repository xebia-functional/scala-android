package com.fortysevendeg.scala.android.ui.akkasimon.actors

import akka.actor.{ActorLogging, ActorSelection, Props}
import com.fortysevendeg.scala.android.ui.akkasimon.actors.ColorActor.LightOn
import com.fortysevendeg.scala.android.ui.akkasimon.fragments.ComputerFragment
import macroid.akkafragments.FragmentActor

import scala.collection.mutable.ArrayBuffer

class ComputerActor extends FragmentActor[ComputerFragment] with ActorLogging {

  import com.fortysevendeg.scala.android.ui.akkasimon.actors.ComputerActor._
  import macroid.akkafragments.FragmentActor._

  var gameList: List[RoundItemActorColor] = List.empty

  val receivedFromUser: ArrayBuffer[ClickedUserColor] = ArrayBuffer.empty

  def receive = receiveUi andThen {
    case NewGame =>
      withUi(f => f.newGame)
    case NewRound(game: List[RoundItemActorColor]) =>
      gameList = game
      receivedFromUser.clear()
      gameList.head.actor ! LightOn(gameList.tail)
    case userEvent: ClickedUserColor =>
      receivedFromUser += userEvent
      withUi(f => f.checkGame(gameList, receivedFromUser.toList map (_.color)))
    case AttachUi(_) =>
    case DetachUi =>
  }
}

object ComputerActor {

  case object NewGame

  case class RoundItemActorColor(actor: ActorSelection, color: Int)

  case class NewRound(game: List[RoundItemActorColor])

  case class ClickedUserColor(color: Int)

  def props = Props(new ComputerActor)
}