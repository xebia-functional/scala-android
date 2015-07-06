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
import macroid.{ActivityContextWrapper, ContextWrapper, Tweak}
import com.fortysevendeg.scala.android.ui.commons.AsyncImageTweaks._

import scala.language.postfixOps

trait Styles {

  val rootStyle = llVertical

  def contentStyle(implicit context: ContextWrapper): Tweak[FrameLayout] =
    vMatchParent +
        vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def fragmentStyle(implicit context: ContextWrapper): Tweak[FrameLayout] = vMatchParent

  def fabStyle(implicit context: ContextWrapper): Tweak[ImageView] = {
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

  def contentRevealStyle(implicit context: ContextWrapper): Tweak[CardView] =
    vMatchParent

  val contentLayoutStyle: Tweak[LinearLayout] =
    vMatchParent +
        llVertical

  def imageStyle(implicit context: ActivityContextWrapper): Tweak[ImageView] =
    lp[LinearLayout](MATCH_PARENT, resGetDimensionPixelSize(R.dimen.circular_reveal_height_image)) +
        srcImage(resGetString(R.string.material_list_url_image)) +
        ivScaleType(ScaleType.CENTER_CROP)

  def textTitleStyle(implicit context: ContextWrapper): Tweak[TextView] =
    tvText(R.string.circular_reveal_title) +
        tvSizeResource(R.dimen.font_size_large) +
        tvText(R.string.circular_reveal_title) +
        vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textMessageStyle(implicit context: ContextWrapper): Tweak[TextView] =
    tvText(R.string.circular_reveal_title) +
        tvSizeResource(R.dimen.font_size_normal) +
        tvNormalLight +
        tvText(R.string.lorem_ipsum_large) +
        vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

}
