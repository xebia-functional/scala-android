package com.fortysevendeg.scala.android.ui.main

import android.graphics.Color
import android.support.v7.widget.{RecyclerView, CardView}
import android.view.Gravity
import android.widget.{TextView, LinearLayout}
import com.fortysevendeg.scala.android.R
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.{Tweak, AppContext}
import scala.language.postfixOps

trait Styles {

  val listStyle: Tweak[RecyclerView] = llMatchWeightVertical

  val contentStyle: Tweak[LinearLayout] = llVertical

}

trait AdapterStyles {

  val cardStyle: Tweak[CardView] = vMatchWidth

  def itemStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    llVertical +
      vMatchWidth +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def itemTopStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    llHorizontal +
      vContentSizeMatchWidth(72 dp) +
      llGravity(Gravity.CENTER_VERTICAL)

  def titleStyle(implicit appContext: AppContext): Tweak[TextView] =
    llWrapWeightHorizontal +
      tvSizeResource(R.dimen.font_size_medium) +
      tvColor(Color.BLACK)

  def descriptionStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_normal) +
      tvNormalLight +
      tvColor(Color.GRAY) +
      tvMaxLines(2)

  def apiStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_micro) +
      tvItalicLight +
      tvColor(Color.WHITE) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

}
