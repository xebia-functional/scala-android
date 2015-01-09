package com.fortysevendeg.scala.android.ui.googlemaps

import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.AppContext
import macroid.FullDsl._

import scala.language.postfixOps

object Styles {

  val contentStyle = llVertical

  val horizontalLinearLayoutStyle = vMatchWidth + llHorizontal

  val buttonsStyle = llWrapWeightHorizontal + tvMaxLines(1)
  
}
