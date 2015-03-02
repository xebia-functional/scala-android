package com.fortysevendeg.scala.android.ui.akkasimon.util

import android.graphics.Color
import com.fortysevendeg.scala.android.ui.akkasimon.actors.ComputerActor.RoundItemActorColor
import com.fortysevendeg.scala.android.ui.akkasimon.util.FragmentEnum._
import macroid.Contexts
import macroid.akkafragments.AkkaFragment
import scala.language.postfixOps
import scala.util.Random

trait SimonAkkaFragment extends AkkaFragment with Contexts[AkkaFragment] {

  val random = Random

  def customActorPath(actorName: String) = s"/user/$actorName"

  lazy val roundActor = actorSystem.actorSelection(customActorPath(ROUND.toLower))

  lazy val computerActor = actorSystem.actorSelection(customActorPath(COMPUTER.toLower))

  lazy val greenActor = actorSystem.actorSelection(customActorPath(GREEN.toLower))

  lazy val redActor = actorSystem.actorSelection(customActorPath(RED.toLower))

  lazy val blueActor = actorSystem.actorSelection(customActorPath(BLUE.toLower))

  lazy val yellowActor = actorSystem.actorSelection(customActorPath(YELLOW.toLower))

  def newRound(): RoundItemActorColor = {
    val actorList = List(
      RoundItemActorColor(greenActor, Color.GREEN),
      RoundItemActorColor(redActor, Color.RED),
      RoundItemActorColor(blueActor, Color.BLUE),
      RoundItemActorColor(yellowActor, Color.YELLOW)
    )

    actorList(random.nextInt(4))
  }
}
