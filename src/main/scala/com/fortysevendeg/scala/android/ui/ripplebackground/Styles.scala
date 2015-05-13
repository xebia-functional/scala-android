package com.fortysevendeg.scala.android.ui.ripplebackground

import android.view.Gravity
import android.view.ViewGroup.LayoutParams._
import android.widget.{FrameLayout, LinearLayout}
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.components.{CircleView, RippleBackgroundView}
import macroid.FullDsl._
import macroid.{ContextWrapper, Tweak}

import scala.language.postfixOps

object Styles {

  val rootStyle: Tweak[LinearLayout] =
    vMatchParent +
      llVertical

  val colorContent: Tweak[FrameLayout] =
    llMatchWeightVertical

  def backgroundStyle(implicit context: ContextWrapper): Tweak[RippleBackgroundView] = {
    val height = resGetDimensionPixelSize(R.dimen.ripple_bg_height_content)
    lp[LinearLayout](MATCH_PARENT, height)
  }

  def circlesContentStyle(implicit context: ContextWrapper): Tweak[LinearLayout] = {
    vMatchWidth +
      llGravity(Gravity.CENTER_HORIZONTAL) +
      llHorizontal
  }

  def circleStyle(implicit context: ContextWrapper): Tweak[CircleView] = {
    val size = resGetDimensionPixelSize(R.dimen.ripple_bg_size_circle)
    val margin = resGetDimensionPixelSize(R.dimen.padding_default)
    val marginTop = resGetDimensionPixelSize(R.dimen.ripple_bg_height_content)
    lp[LinearLayout](size, size) +
      vMargin(
        marginLeft = margin,
        marginTop = margin + marginTop,
        marginRight = margin,
        marginBottom = margin
      )
  }

}
