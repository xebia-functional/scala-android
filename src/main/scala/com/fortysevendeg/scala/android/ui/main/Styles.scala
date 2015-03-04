package com.fortysevendeg.scala.android.ui.main

import android.support.v7.widget.{CardView, RecyclerView}
import android.view.{View, ViewGroup, Gravity}
import android.view.ViewGroup.LayoutParams._
import android.widget.{ImageView, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.ThemeExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
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
      vPadding(
        paddingRight = resGetDimensionPixelSize(R.dimen.padding_default_small),
        paddingLeft = resGetDimensionPixelSize(R.dimen.padding_default_small))

  def lineHorizontalStyle(implicit appContext: AppContext): Tweak[ImageView] =
    lp[LinearLayout](MATCH_PARENT, resGetDimensionPixelSize(R.dimen.line)) +
      vBackgroundColorResource(R.color.main_list_line)

  val bottomContentStyle: Tweak[LinearLayout] =
    vMatchWidth +
      llHorizontal +
      llGravity(Gravity.CENTER_VERTICAL)

  def bottomUserContentStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    llMatchWeightHorizontal +
      llHorizontal +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default)) +
      llGravity(Gravity.CENTER_VERTICAL)

  def avatarStyle(implicit appContext: AppContext): Tweak[ImageView] = {
    val size = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    lp[LinearLayout](size, size) +
      vMargins(resGetDimensionPixelSize(R.dimen.padding_default_small))
  }

  def userNameStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_normal) +
      tvNormalLight +
      tvColorResource(R.color.primary)

  def twitterStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_small) +
      tvNormalLight +
      tvColorResource(R.color.main_list_secondary)

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

  def levelStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_small) +
      tvNormalLight +
      tvColorResource(R.color.main_list_secondary) +
      vMinWidth(resGetDimensionPixelSize(R.dimen.main_list_min_with_levels_tag))

  def levelNumberStyle(implicit appContext: AppContext): Tweak[TextView] =
    vWrapContent +
    tvSizeResource(R.dimen.font_size_micro) +
      tvColorResource(R.color.main_list_text_level_number) +
      vBackgroundColorResource(R.color.main_list_background_level_number) +
      vPadding(
        paddingLeft = resGetDimensionPixelSize(R.dimen.padding_default_small),
        paddingRight = resGetDimensionPixelSize(R.dimen.padding_default_small)) +
      llLayoutMargin(marginRight = resGetDimensionPixelSize(R.dimen.padding_default_small))

}
