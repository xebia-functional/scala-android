package com.fortysevendeg.scala.android.ui.akkasimon.fragments

import com.fortysevendeg.scala.android.ui.akkasimon.actors.ComputerActor._
import com.fortysevendeg.scala.android.ui.akkasimon.actors.ComputerActor.RoundItemActorColor
import com.fortysevendeg.scala.android.ui.akkasimon.util.SimonAkkaFragment
import macroid._
import ComputerFragment._

class ComputerFragment
  extends SimonAkkaFragment {

  lazy val actorName = getArguments.getString(nameComputerKey)

  lazy val actor = Some(actorSystem.actorSelection(s"/user/$actorName"))

  def newGame = Ui(computerActor ! ResetRound(newRound()))

  def checkGame(gameList: List[RoundItemActorColor], userClicks: List[Int]) = Ui {

    if (userClicks.size == gameList.size) {

      val colors = gameList map (_.color)

      if (userClicks.zip(colors).exists(t => t._1 != t._2))
        computerActor ! GameOver
      else
        computerActor ! NextRound(newRound())
    }
  }
}

object ComputerFragment {
  val nameComputerKey = "name"
}
