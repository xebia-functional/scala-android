package com.fortysevendeg.scala.android.ui.apirequest

import android.view.Gravity
import android.widget.{ImageView, ProgressBar, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.scala.android.R
import macroid.{ContextWrapper, Tweak}

import scala.language.postfixOps

object Styles {

  val rootStyle = llVertical

  val contentStyle = vMatchParent

  val errorLayoutStyle = vMatchParent

  val errorContentStyle: Tweak[LinearLayout] =
    vWrapContent +
      flLayoutGravity(Gravity.CENTER) +
      llGravity(Gravity.CENTER_HORIZONTAL) +
      llVertical +
      vGone

  val progressContentStyle: Tweak[LinearLayout] =
    vWrapContent +
      flLayoutGravity(Gravity.CENTER) +
      llGravity(Gravity.CENTER_HORIZONTAL) +
      llVertical +
      vGone

  val progressBarStyle: Tweak[ProgressBar] =
    vWrapContent +
      flLayoutGravity(Gravity.CENTER)

  def messageStyle(implicit context: ContextWrapper): Tweak[TextView] =
    vWrapContent +
      tvGravity(Gravity.CENTER) +
      tvColorResource(R.color.text_error_message) +
      tvSizeResource(R.dimen.text_size_forecast_error_message) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def buttonStyle(implicit context: ContextWrapper): Tweak[TextView] =
    vWrapContent +
      vMinWidth(resGetDimensionPixelSize(R.dimen.width_forecast_error_button)) +
      tvColorResource(R.color.text_error_button) +
      vBackground(R.drawable.background_error_button) +
      tvAllCaps +
      tvSizeResource(R.dimen.text_size_forecast_error_button) +
      tvGravity(Gravity.CENTER)

  val forecastFragmentLayoutStyle = vMatchParent

  def forecastLayoutStyle(implicit context: ContextWrapper): Tweak[LinearLayout] =
    vMatchParent +
      llVertical +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def forecastLocationLayoutStyle(implicit context: ContextWrapper): Tweak[LinearLayout] = 
    vMatchWidth +
      llHorizontal +
      llGravity(Gravity.CENTER_VERTICAL)

  def forecastDetailLayoutStyle(implicit context: ContextWrapper): Tweak[LinearLayout] =
    vMatchParent +
      llVertical +
      llGravity(Gravity.CENTER)

  def markerImageViewStyle(implicit context: ContextWrapper): Tweak[ImageView] =
    vWrapContent +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default)) +
      ivSrc(R.drawable.map_marker)

  def locationTextViewStyle(implicit context: ContextWrapper) =
    vWrapContent +
      tvSizeResource(R.dimen.text_size_forecast_location) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  val forecastImageViewStyle = vWrapContent

  def temperatureTextViewStyle(implicit context: ContextWrapper) =
    vWrapContent +
      tvGravity(Gravity.CENTER) +
      tvSizeResource(R.dimen.text_size_forecast_temperature) +
      tvBoldCondensed +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def aboutDialogLayoutStyle(implicit context: ContextWrapper) =
    vMatchParent +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  val dialogTextViewStyle =
    vWrapContent +
      tvText(R.string.forecast_attribution)

}
