package com.fortysevendeg.scala.android.ui.akkasimon

import android.view.Gravity
import android.widget.{TextView, Button, FrameLayout, LinearLayout}
import com.fortysevendeg.macroid.extras.DeviceVersion.Lollipop
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import macroid.FullDsl._
import macroid.{Tweak, AppContext}

import scala.language.postfixOps

trait Styles {

  def simonButton(color: Int, alpha: Float = 0.3f)(implicit appCtx: AppContext) =
    vBackgroundColor(color) +
      vAlpha(alpha)

  val rootStyle: Tweak[LinearLayout] = llVertical

  val contentStyle: Tweak[FrameLayout] = llMatchWeightVertical

  val optionsContentStyle: Tweak[LinearLayout] =
    vMatchParent +
      llGravity(Gravity.CENTER) +
      llVertical

  def messageStyle(implicit appCtx: AppContext): Tweak[TextView] =
    vWrapContent +
      tvText(R.string.simon_welcome) +
      tvSizeResource(R.dimen.font_size_large) +
      tvAllCaps +
      tvGravity(Gravity.CENTER) +
      vPadding(paddingBottom = resGetDimensionPixelSize(R.dimen.padding_default_xlarge)) +
      tvNormalLight

  def buttonsStyle(implicit appCtx: AppContext): Tweak[Button] = {
    val size = resGetDimensionPixelSize(R.dimen.size_fab_default)
    lp[LinearLayout](size, size) +
      tvText(R.string.simon_start) +
      vBackground(R.drawable.background_default_fab) +
      (Lollipop ifSupportedThen vElevation(resGetDimension(R.dimen.padding_default_small)) getOrElse Tweak.blank)
  }

  val gameContentStyle: Tweak[LinearLayout] =
    vMatchParent +
      llVertical +
      vGone

  def roundsStyle(implicit appCtx: AppContext): Tweak[TextView] =
    vMatchWidth +
      tvGravity(Gravity.CENTER) +
      tvAllCaps +
      tvSizeResource(R.dimen.font_size_large) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default)) +
      tvNormalLight

  val simonContainerStyle: Tweak[LinearLayout] =
    llMatchWeightVertical +
      llHorizontal

  val columnStyle: Tweak[LinearLayout] =
    llMatchWeightHorizontal +
      llVertical

  val rowStyle: Tweak[FrameLayout] = llMatchWeightVertical

}
