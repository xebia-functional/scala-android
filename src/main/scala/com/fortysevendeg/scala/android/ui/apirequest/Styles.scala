package com.fortysevendeg.scala.android.ui.apirequest

import android.view.Gravity
import android.widget.{ProgressBar, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import macroid.FullDsl._
import macroid.{AppContext, Tweak}

import scala.language.postfixOps

object Styles {

  val rootStyle = llVertical

  val contentStyle = vMatchParent + vPaddings(24)

  val errorLayoutStyle = vMatchParent

  val errorContentStyle: Tweak[LinearLayout] =
    vWrapContent +
      flLayoutGravity(Gravity.CENTER) +
      llGravity(Gravity.CENTER_HORIZONTAL) +
      llVertical +
      vGone

  val progressBarStyle: Tweak[ProgressBar] =
    vWrapContent +
      flLayoutGravity(Gravity.CENTER)

  def errorMessageStyle(implicit appContext: AppContext): Tweak[TextView] =
    vWrapContent +
      tvGravity(Gravity.CENTER) +
      tvColorResource(R.color.text_error_message) +
      tvSize(18) +
      vPaddings(10 dp)

  def errorButtonStyle(implicit appContext: AppContext): Tweak[TextView] =
    vWrapContent +
      vMinWidth(160 dp) +
      tvText(R.string.try_again_button) +
      tvColorResource(R.color.text_error_button) +
      vBackground(R.drawable.background_error_button) +
      tvAllCaps +
      tvSize(14) +
      tvGravity(Gravity.CENTER)

  val forecastLayoutStyle = vMatchParent

  val forecastDetailLayoutStyle = vMatchParent

  def textViewStyle(implicit appContext: AppContext): Tweak[TextView] =
    vWrapContent +
      tvGravity(Gravity.CENTER) +
      tvSize(18) +
      vPaddings(10 dp)

}
