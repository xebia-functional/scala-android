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
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext, Tweak}

import scala.language.postfixOps

trait Styles {

  val listStyle: Tweak[RecyclerView] = llMatchWeightVertical

  def contentStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    llVertical +
      vBackgroundColorResource(R.color.main_list_background)

}

trait AdapterStyles {

  def cardStyle(implicit activityContext: ActivityContext, appContext: AppContext): Tweak[CardView] =
    vMatchWidth +
      flForeground(themeGetDrawable(android.R.attr.selectableItemBackground))

  def itemStyle(implicit activityContext: ActivityContext, appContext: AppContext): Tweak[LinearLayout] =
    llVertical +
      vMatchWidth

  def itemTopStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    llHorizontal +
      vMatchWidth +
      llGravity(Gravity.CENTER_VERTICAL) +
      vPadding(
        paddingTop = resGetDimensionPixelSize(R.dimen.padding_default_xlarge),
        paddingBottom = resGetDimensionPixelSize(R.dimen.padding_default),
        paddingLeft = resGetDimensionPixelSize(R.dimen.padding_default_xlarge),
        paddingRight = resGetDimensionPixelSize(R.dimen.padding_default_xlarge))

  def titleStyle(implicit appContext: AppContext): Tweak[TextView] =
    llWrapWeightHorizontal +
      tvSizeResource(R.dimen.font_size_medium) +
      tvColorResource(R.color.primary)

  def descriptionStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_normal) +
      tvNormalLight +
      tvColorResource(R.color.main_list_description) +
      tvMaxLines(3) +
      vPadding(
        paddingBottom = resGetDimensionPixelSize(R.dimen.padding_default_xlarge),
        paddingLeft = resGetDimensionPixelSize(R.dimen.padding_default_xlarge),
        paddingRight = resGetDimensionPixelSize(R.dimen.padding_default_xlarge))

  def apiStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_micro) +
      tvColorResource(R.color.main_list_api) +
      vPaddings(
        paddingTopBottom = resGetDimensionPixelSize(R.dimen.padding_default_micro),
        paddingLeftRight = resGetDimensionPixelSize(R.dimen.padding_default_small))

  def lineHorizontalStyle(implicit appContext: AppContext): Tweak[ImageView] =
    lp[LinearLayout](MATCH_PARENT, resGetDimensionPixelSize(R.dimen.line)) +
      vBackgroundColorResource(R.color.main_list_line)

  val bottomContentStyle: Tweak[LinearLayout] =
    vMatchWidth +
      llHorizontal +
      llGravity(Gravity.CENTER_VERTICAL)

  def bottomUserContentStyle(implicit appContext: AppContext, activityContext: ActivityContext): Tweak[LinearLayout] =
    llMatchWeightHorizontal +
      llHorizontal +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default)) +
      llGravity(Gravity.CENTER_VERTICAL) +
      vBackground(themeGetDrawable(android.R.attr.selectableItemBackground))

  def avatarStyle(implicit appContext: AppContext): Tweak[ImageView] = {
    val size = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    lp[LinearLayout](size, size) +
      ivScaleType(ScaleType.CENTER_CROP) +
      vMargins(resGetDimensionPixelSize(R.dimen.padding_default_small))
  }

  def userNameStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_normal) +
      tvNormalLight +
      tvColorResource(R.color.primary) +
      tvMaxLines(1) +
      tvEllipsize(TruncateAt.END)

  def twitterStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_small) +
      tvNormalLight +
      tvColorResource(R.color.main_list_secondary) +
      tvMaxLines(1) +
      tvEllipsize(TruncateAt.END)

  def userNameContentStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    llMatchWeightHorizontal +
      llVertical +
      vPadding(paddingLeft = resGetDimensionPixelSize(R.dimen.padding_default_small)) +
      llGravity(Gravity.CENTER_VERTICAL)

  def lineVerticalStyle(implicit appContext: AppContext): Tweak[ImageView] =
    lp[LinearLayout](resGetDimensionPixelSize(R.dimen.line), MATCH_PARENT) +
      vBackgroundColorResource(R.color.main_list_line)

  def bottomLevelsContentStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    llMatchWeightHorizontal +
      llVertical +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))  +
      llGravity(Gravity.CENTER_VERTICAL)
  
  def levelItemContentStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    vWrapContent +
      vPadding(paddingBottom = resGetDimensionPixelSize(R.dimen.padding_default_micro))

  def levelStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_small) +
      tvNormalLight +
      tvColorResource(R.color.main_list_secondary) +
      vMinWidth(resGetDimensionPixelSize(R.dimen.main_list_min_width_levels_tag))

  def levelNumberStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_small) +
      tvColorResource(R.color.main_list_tag) +
      tvNormalLight +
      vPaddings(
        paddingTopBottom = 0,
        paddingLeftRight = resGetDimensionPixelSize(R.dimen.padding_default_small))

}
