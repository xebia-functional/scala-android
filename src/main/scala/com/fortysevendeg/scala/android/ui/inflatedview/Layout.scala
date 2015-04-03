package com.fortysevendeg.scala.android.ui.inflatedview

import android.support.v7.app.ActionBarActivity
import android.widget._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.circularreveal.Styles
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid._

trait Layout
    extends ToolbarLayout
    with IdGeneration
    with Styles {
  self: Contexts[ActionBarActivity] =>

  import com.fortysevendeg.scala.android.ui.commons.ActivityUtils._

  lazy val text = find[EditText](R.id.textxml)

  lazy val button = find[Button](R.id.buttonxml)

  def initLayout = runUi {
      button <~ On.click {
        text <~ tvText("Button clicked!")
      }
  }
}