package com.fortysevendeg.scala.android.ui.akkasimon.fragments

import android.widget.Button
import macroid.FullDsl._
import macroid._
import macroid.akkafragments.AkkaFragment

class ComputerFragment extends AkkaFragment with Contexts[AkkaFragment] {

  lazy val actorName = getArguments.getString("name")

  lazy val actor = Some(actorSystem.actorSelection(s"/user/$actorName"))

  var simonColor = slot[Button]

  def receive = {
    println("Computer fragment...")
  }
}