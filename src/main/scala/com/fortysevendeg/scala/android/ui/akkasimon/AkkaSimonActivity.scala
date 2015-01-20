package com.fortysevendeg.scala.android.ui.akkasimon

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.MenuItem
import com.fortysevendeg.scala.android.ui.akkasimon.actors.{RoundActor, ComputerActor, ColorActor}
import com.fortysevendeg.scala.android.ui.akkasimon.util.FragmentEnum
import FragmentEnum._
import macroid.Contexts
import macroid.akkafragments.AkkaActivity

class AkkaSimonActivity
    extends FragmentActivity
    with Contexts[FragmentActivity]
    with AkkaActivity
    with Layout {

  val actorSystemName = "simonsystem"

  lazy val computer = actorSystem.actorOf(ComputerActor.props, "computer")
  lazy val round = actorSystem.actorOf(RoundActor.props, "round")
  lazy val green = actorSystem.actorOf(ColorActor.props, GREEN.toLower)
  lazy val red = actorSystem.actorOf(ColorActor.props, RED.toLower)
  lazy val blue = actorSystem.actorOf(ColorActor.props, BLUE.toLower)
  lazy val yellow = actorSystem.actorOf(ColorActor.props, YELLOW.toLower)

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    (computer, round, green, red, blue, yellow)

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