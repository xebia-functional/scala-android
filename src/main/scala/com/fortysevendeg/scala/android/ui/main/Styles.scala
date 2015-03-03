package com.fortysevendeg.scala.android.ui.main

import android.support.v7.widget.{CardView, RecyclerView}
import android.view.ViewGroup.LayoutParams._
import android.view.{ViewGroup, View, Gravity}
import android.widget.{ImageView, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
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

  def cardStyle(implicit activityContext: ActivityContext, appContext: AppContext): Tweak[CardView] = vMatchWidth +
    Tweak[CardView] {
      view =>
        val a = activityContext.get.getTheme.obtainStyledAttributes(Array(android.support.v7.appcompat.R.attr.selectableItemBackground))
        val attributeResourceId = a.getResourceId(0, 0)
        val drawable = activityContext.get.getResources.getDrawable(attributeResourceId)
        a.recycle()
        view.setForeground(drawable)
    }

  def itemStyle(implicit activityContext: ActivityContext, appContext: AppContext): Tweak[LinearLayout] =
    llVertical +
      vMatchWidth +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def itemTopStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    llHorizontal +
      vMatchWidth +
      llGravity(Gravity.CENTER_VERTICAL)

  def titleStyle(implicit appContext: AppContext): Tweak[TextView] =
    llWrapWeightHorizontal +
      tvSizeResource(R.dimen.font_size_medium) +
      tvColorResource(R.color.primary)

  def descriptionStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_normal) +
      tvNormalLight +
      tvColorResource(R.color.main_list_description) +
      tvMaxLines(3) +
      vPadding(paddingTop = resGetDimensionPixelSize(R.dimen.padding_default))

  def apiStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_micro) +
      tvColorResource(R.color.main_list_api) +
      vPadding(
        paddingRight = resGetDimensionPixelSize(R.dimen.padding_default_small),
        paddingLeft = resGetDimensionPixelSize(R.dimen.padding_default_small))

  def lineHorizontalStyle(implicit appContext: AppContext): Tweak[ImageView] =
    lp[LinearLayout](MATCH_PARENT, resGetDimensionPixelSize(R.dimen.line)) +
      vBackgroundColorResource(R.color.main_list_line)

  val bottomContentStyle: Tweak[LinearLayout] = vMatchWidth

  val bottomUserContentStyle: Tweak[LinearLayout] = llMatchWeightHorizontal

  def lineVerticalStyle(implicit appContext: AppContext): Tweak[ImageView] =
    lp[LinearLayout](resGetDimensionPixelSize(R.dimen.line), MATCH_PARENT) +
      vBackgroundColorResource(R.color.main_list_line)

  val bottomLevelsContentStyle: Tweak[LinearLayout] = llMatchWeightHorizontal

}
