package com.fortysevendeg.scala.android.ui.googlemaps

import android.widget.{Button, LinearLayout}
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.Tweak

import scala.language.postfixOps

object Styles {

  val contentStyle: Tweak[LinearLayout] = llVertical

  val horizontalLinearLayoutStyle: Tweak[LinearLayout] =
    vMatchWidth +
      llHorizontal

  val buttonsStyle: Tweak[Button] = llWrapWeightHorizontal + tvMaxLines(1)
  
}
