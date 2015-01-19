package com.fortysevendeg.scala.android.ui.akkasimon

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.MenuItem
import com.fortysevendeg.scala.android.ui.akkasimon.actors.SimonComputerActor.NewGame
import com.fortysevendeg.scala.android.ui.akkasimon.actors.{SimonComputerActor, SimonColorActor}
import com.fortysevendeg.scala.android.ui.akkasimon.util.SimonColor
import SimonColor._
import macroid.Contexts
import macroid.akkafragments.AkkaActivity

class AkkaSimonActivity
    extends FragmentActivity
    with Contexts[FragmentActivity]
    with AkkaActivity
    with Layout {

  val actorSystemName = "simonsystem"

  lazy val computer = actorSystem.actorOf(SimonComputerActor.props, "computer")
  lazy val green = actorSystem.actorOf(SimonColorActor.props, GREEN.toLower)
  lazy val red = actorSystem.actorOf(SimonColorActor.props, RED.toLower)
  lazy val blue = actorSystem.actorOf(SimonColorActor.props, BLUE.toLower)
  lazy val yellow = actorSystem.actorOf(SimonColorActor.props, YELLOW.toLower)

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    (computer, green, red, blue, yellow)

    setContentView(layout(actorSystem))
  }

  override def onStart() = {
    super.onStart()
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case android.R.id.home => {
        finish()
        false
      }
    }
    super.onOptionsItemSelected(item)
  }
}