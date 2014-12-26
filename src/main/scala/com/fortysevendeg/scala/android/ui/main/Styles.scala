package com.fortysevendeg.scala.android.ui.main

import android.graphics.Color
import android.view.Gravity
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.AppContext

object Styles {

  val listStyle = llMatchWeightVertical

  val contentStyle = llVertical

  val cardStyle = vMatchWidth

  def itemStyle(implicit appContext: AppContext) = llVertical + vMatchWidth + vPaddings(12 dp)

  def itemTopStyle(implicit appContext: AppContext) = llHorizontal + vContentSizeMatchWidth(72 dp) + llGravity(Gravity.CENTER_VERTICAL)

  val titleStyle = llWrapWeightHorizontal + tvSize(18) + tvColor(Color.BLACK)

  val descriptionStyle = tvSize(14) + tvNormalLight + tvColor(Color.GRAY) + tvMaxLines(2)

  def apiStyle(implicit appContext: AppContext) = tvSize(10) + tvItalicLight + tvColor(Color.WHITE) + vPaddings(8 dp)

}
