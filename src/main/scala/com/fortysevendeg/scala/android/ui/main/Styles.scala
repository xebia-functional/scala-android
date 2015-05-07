package com.fortysevendeg.scala.android.ui.main

import android.support.v7.widget.{CardView, RecyclerView}
import android.text.TextUtils.TruncateAt
import android.view.Gravity
import android.view.ViewGroup.LayoutParams._
import android.widget.ImageView.ScaleType
import android.widget.{ImageView, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ThemeExtras._
import com.fortysevendeg.macroid.extras.ViewGroupTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import macroid.FullDsl._
import macroid.{ActivityContextWrapper, ContextWrapper, Tweak}

import scala.language.postfixOps

trait Styles {

  def listStyle(implicit context: ContextWrapper): Tweak[RecyclerView] =
    llMatchWeightVertical +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default)) +
      vgClipToPadding(false)

  def contentStyle(implicit context: ContextWrapper): Tweak[LinearLayout] =
    llVertical +
      vBackgroundColorResource(R.color.main_list_background)

}

trait AdapterStyles {

  def cardStyle(implicit activityContext: ActivityContextWrapper): Tweak[CardView] =
    vMatchWidth +
      (themeGetDrawable(android.R.attr.selectableItemBackground) map flForeground getOrElse Tweak.blank)

  def itemStyle: Tweak[LinearLayout] =
    llVertical +
      vMatchWidth

  def itemTopStyle(implicit context: ContextWrapper): Tweak[LinearLayout] =
    llHorizontal +
      vMatchWidth +
      llGravity(Gravity.CENTER_VERTICAL) +
      vPadding(
        paddingTop = resGetDimensionPixelSize(R.dimen.padding_default_xlarge),
        paddingBottom = resGetDimensionPixelSize(R.dimen.padding_default),
        paddingLeft = resGetDimensionPixelSize(R.dimen.padding_default_xlarge),
        paddingRight = resGetDimensionPixelSize(R.dimen.padding_default_xlarge))

  def titleStyle(implicit context: ContextWrapper): Tweak[TextView] =
    llWrapWeightHorizontal +
      tvSizeResource(R.dimen.font_size_medium) +
      tvColorResource(R.color.primary)

  def descriptionStyle(implicit context: ContextWrapper): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_normal) +
      tvNormalLight +
      tvColorResource(R.color.main_list_description) +
      tvMaxLines(3) +
      vPadding(
        paddingBottom = resGetDimensionPixelSize(R.dimen.padding_default_xlarge),
        paddingLeft = resGetDimensionPixelSize(R.dimen.padding_default_xlarge),
        paddingRight = resGetDimensionPixelSize(R.dimen.padding_default_xlarge))

  def apiStyle(implicit context: ContextWrapper): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_micro) +
      tvColorResource(R.color.main_list_api) +
      vPaddings(
        paddingTopBottom = resGetDimensionPixelSize(R.dimen.padding_default_micro),
        paddingLeftRight = resGetDimensionPixelSize(R.dimen.padding_default_small))

  def lineHorizontalStyle(implicit context: ContextWrapper): Tweak[ImageView] =
    lp[LinearLayout](MATCH_PARENT, resGetDimensionPixelSize(R.dimen.line)) +
      vBackgroundColorResource(R.color.main_list_line)

  val bottomContentStyle: Tweak[LinearLayout] =
    vMatchWidth +
      llHorizontal +
      llGravity(Gravity.CENTER_VERTICAL)

  def bottomUserContentStyle(implicit activityContext: ActivityContextWrapper): Tweak[LinearLayout] =
    llMatchWeightHorizontal +
      llHorizontal +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default)) +
      llGravity(Gravity.CENTER_VERTICAL) +
      (themeGetDrawable(android.R.attr.selectableItemBackground) map vBackground getOrElse Tweak.blank)

  def avatarStyle(implicit context: ContextWrapper): Tweak[ImageView] = {
    val size = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    lp[LinearLayout](size, size) +
      ivScaleType(ScaleType.CENTER_CROP) +
      vMargins(resGetDimensionPixelSize(R.dimen.padding_default_small))
  }

  def userNameStyle(implicit context: ContextWrapper): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_normal) +
      tvNormalLight +
      tvColorResource(R.color.primary) +
      tvMaxLines(1) +
      tvEllipsize(TruncateAt.END)

  def twitterStyle(implicit context: ContextWrapper): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_small) +
      tvNormalLight +
      tvColorResource(R.color.main_list_secondary) +
      tvMaxLines(1) +
      tvEllipsize(TruncateAt.END)

  def userNameContentStyle(implicit context: ContextWrapper): Tweak[LinearLayout] =
    llMatchWeightHorizontal +
      llVertical +
      vPadding(paddingLeft = resGetDimensionPixelSize(R.dimen.padding_default_small)) +
      llGravity(Gravity.CENTER_VERTICAL)

  def lineVerticalStyle(implicit context: ContextWrapper): Tweak[ImageView] =
    lp[LinearLayout](resGetDimensionPixelSize(R.dimen.line), MATCH_PARENT) +
      vBackgroundColorResource(R.color.main_list_line)

  def bottomLevelsContentStyle(implicit context: ContextWrapper): Tweak[LinearLayout] =
    llMatchWeightHorizontal +
      llVertical +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))  +
      llGravity(Gravity.CENTER_VERTICAL)
  
  def levelItemContentStyle(implicit context: ContextWrapper): Tweak[LinearLayout] =
    vWrapContent +
      vPadding(paddingBottom = resGetDimensionPixelSize(R.dimen.padding_default_micro))

  def levelStyle(implicit context: ContextWrapper): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_small) +
      tvNormalLight +
      tvColorResource(R.color.main_list_secondary) +
      vMinWidth(resGetDimensionPixelSize(R.dimen.main_list_min_width_levels_tag))

  def levelTypeStyle(implicit context: ContextWrapper): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_small) +
      tvColorResource(R.color.main_list_tag) +
      tvNormalLight +
      vPaddings(
        paddingTopBottom = 0,
        paddingLeftRight = resGetDimensionPixelSize(R.dimen.padding_default_small)) +
      tvMaxLines(1) +
      tvEllipsize(TruncateAt.END)

}
