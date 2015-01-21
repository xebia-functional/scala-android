package com.fortysevendeg.scala.android.ui.akkasimon.util

import com.fortysevendeg.scala.android.ui.akkasimon.util.FragmentEnum._
import macroid.Contexts
import macroid.akkafragments.AkkaFragment

trait SimonAkkaFragment extends AkkaFragment with Contexts[AkkaFragment] {

  def customActorPath(actorName: String) = s"/user/$actorName"

  lazy val roundActor = actorSystem.actorSelection(customActorPath(ROUND.toLower))

  lazy val computerActor = actorSystem.actorSelection(customActorPath(COMPUTER.toLower))

  lazy val greenActor = actorSystem.actorSelection(customActorPath(GREEN.toLower))

  lazy val redActor = actorSystem.actorSelection(customActorPath(RED.toLower))

  lazy val blueActor = actorSystem.actorSelection(customActorPath(BLUE.toLower))

  lazy val yellowActor = actorSystem.actorSelection(customActorPath(YELLOW.toLower))
}
