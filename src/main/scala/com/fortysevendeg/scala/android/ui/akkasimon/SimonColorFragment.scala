package com.fortysevendeg.scala.android.ui.akkasimon

import android.os.Bundle
import android.view.{LayoutInflater, ViewGroup}
import android.widget.{Button, LinearLayout}
import com.fortysevendeg.scala.android.ui.akkasimon.Styles._
import macroid.FullDsl._
import macroid._
import macroid.akkafragments.AkkaFragment

import scala.concurrent.ExecutionContext.Implicits.global

class SimonColorFragment extends AkkaFragment with Contexts[AkkaFragment] {

  lazy val actorName = getArguments.getString("name")
  lazy val color = getArguments.getInt("color")

  lazy val actor = Some(actorSystem.actorSelection(s"/user/$actorName"))

  var simonColor = slot[Button]

  def receive = lightColor(color)

  def lightColor(c: Int = color) = simonColor <~ vAlpha(1f) <~~ delay(1000) <~ simonButton(c) <~~ delay(1000)

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle) = getUi {
    l[LinearLayout](
      w[Button] <~ wire(simonColor) <~ simonButton(color) <~ On.click(lightColor())
    )
  }
}