package com.fortysevendeg.scala.android.ui.akkasimon.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.{LayoutInflater, ViewGroup}
import android.widget.TextView
import com.fortysevendeg.scala.android.ui.akkasimon.actors.ComputerActor.{NewRound, RoundItemActorColor}
import com.fortysevendeg.scala.android.ui.akkasimon.util.SimonAkkaFragment
import macroid.FullDsl._
import macroid._

import scala.language.postfixOps
import scala.util.Random

class
RoundFragment  extends SimonAkkaFragment {

  lazy val actorName = getArguments.getString("name")

  lazy val actor = Some(actorSystem.actorSelection(s"/user/$actorName"))

  var round = 1

  val random = Random

  var tvwRound = slot[TextView]

  def youLoose = Ui(tvwRound.get.setText("You Loose"))

  def resetRound = Ui {
    round = 1
    tvwRound.get.setText(round.toString)
  }

  def incrementRound = Ui {
    round += 1
    tvwRound.get.setText(round.toString)
  }

  def newRound = Ui {

    val actorList = List(
        RoundItemActorColor(greenActor, Color.GREEN),
        RoundItemActorColor(redActor, Color.RED),
        RoundItemActorColor(blueActor, Color.BLUE),
        RoundItemActorColor(yellowActor, Color.YELLOW)
      )

    val gameSequenceList = 0 until round map (_ => actorList(random.nextInt(4))) toList

    computerActor ! NewRound(gameSequenceList)
  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle) = getUi {
    w[TextView] <~ wire(tvwRound) <~ text("1")
  }
}