package com.fortysevendeg.scala.android.ui.inflatedview

import android.support.v7.app.ActionBarActivity
import android.widget._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid._

trait Layout
    extends ToolbarLayout
    with IdGeneration {
  self: Contexts[ActionBarActivity] =>

  import com.fortysevendeg.scala.android.ui.commons.ExtraTweakOpsUtils._
  import com.fortysevendeg.scala.android.ui.commons.InflateUtils._

  lazy val sampleInflateLayout = inflateLayout(Some(R.layout.sample))

  lazy val text = inflatedSlot[EditText](R.id.textxml, sampleInflateLayout)

  lazy val button = inflatedSlot[Button](R.id.buttonxml, sampleInflateLayout)

  def layout = getUi(
    button <~? On.Click {
      text <~? tvText("Clicked")
    }
  )
}