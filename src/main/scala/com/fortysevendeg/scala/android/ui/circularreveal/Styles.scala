package com.fortysevendeg.scala.android.ui.circularreveal

import android.graphics.Color
import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.ViewGroup.LayoutParams._
import android.widget.ImageView.ScaleType
import android.widget.{FrameLayout, ImageView, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.DeviceVersion._
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.components.IconTypes._
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawable
import macroid.FullDsl._
import macroid.{AppContext, Tweak}

import scala.language.postfixOps

trait Styles {

  val rootStyle = llVertical

  def contentStyle(implicit appContext: AppContext): Tweak[FrameLayout] =
    vMatchParent +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def fragmentStyle(implicit appContext: AppContext): Tweak[FrameLayout] = vMatchParent

  def fabStyle(implicit appContext: AppContext): Tweak[ImageView] = {
    val size = resGetDimensionPixelSize(R.dimen.size_fab_default)
    lp[FrameLayout](size, size) +
      flLayoutGravity(Gravity.RIGHT | Gravity.BOTTOM) +
      vBackground(R.drawable.background_default_fab) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default_xlarge)) +
      ivSrc(new PathMorphDrawable(
        defaultIcon = ADD,
        defaultStroke = resGetDimensionPixelSize(R.dimen.circular_reveal_fab_stroke),
        defaultColor = Color.WHITE
      )) +
      vMargins(resGetDimensionPixelSize(R.dimen.padding_default)) +
      (Lollipop ifSupportedThen vElevation(resGetDimension(R.dimen.padding_default_small)) getOrElse Tweak.blank)
  }

}

trait FragmentStyles {

  def contentRevealStyle(implicit appContext: AppContext): Tweak[CardView] =
    vMatchParent

  val contentLayoutStyle: Tweak[LinearLayout] =
    vMatchParent +
      llVertical

  def imageStyle(implicit appContext: AppContext): Tweak[ImageView] =
    lp[LinearLayout](MATCH_PARENT, resGetDimensionPixelSize(R.dimen.circular_reveal_height_image)) +
      ivSrc(R.drawable.photo_1) +
      ivScaleType(ScaleType.CENTER_CROP)

  def textTitleStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvText(R.string.circular_reveal_title) +
      tvSizeResource(R.dimen.font_size_large) +
      tvText(R.string.circular_reveal_title) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textMessageStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvText(R.string.circular_reveal_title) +
      tvSizeResource(R.dimen.font_size_normal) +
      tvNormalLight +
      tvText(R.string.lorem_ipsum_large) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

}
