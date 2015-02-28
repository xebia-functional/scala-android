package com.fortysevendeg.scala.android.ui.akkasimon

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.MenuItem
import com.fortysevendeg.scala.android.ui.akkasimon.actors.{RoundActor, ComputerActor, ColorActor}
import com.fortysevendeg.scala.android.ui.akkasimon.util.FragmentEnum
import FragmentEnum._
import macroid.FullDsl._
import macroid.{Ui, Contexts}
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

    setContentView(layout)
  }

  override def onDestroy(): Unit = {
    super.onDestroy()
    actorSystem.shutdown()
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

  def loose = goToOptions

}