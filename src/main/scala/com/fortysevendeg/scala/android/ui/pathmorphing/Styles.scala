package com.fortysevendeg.scala.android.ui.pathmorphing

import android.view.Gravity
import android.widget._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.SeekBarTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawable
import com.fortysevendeg.scala.android.ui.components.IconTypes._
import macroid.{Tweak, AppContext}
import macroid.FullDsl._
import scala.language.postfixOps

trait Styles {

  val rootStyle: Tweak[LinearLayout] =
    vMatchParent +
      llVertical

  val contentStyle: Tweak[LinearLayout] =
    vMatchParent +
      llVertical

  def scrollViewStyle(implicit appContext: AppContext): Tweak[ScrollView] =
    vMatchParent +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  val horizontalLinearLayoutStyle: Tweak[LinearLayout] = llHorizontal

  val verticalLinearLayoutStyle: Tweak[LinearLayout] =
    vMatchParent +
      llVertical

  val contentItemsRadiosStyle: Tweak[LinearLayout] = llMatchWeightHorizontal

  val titleStyle: Tweak[TextView] = tvNormalLight

  val strokeStyle: Tweak[SeekBar] =
    sbMax(4) +
      sbProgress(0) +
      vMatchWidth

  val sizeStyle: Tweak[SeekBar] =
    sbMax(3) +
      sbProgress(0) +
      vMatchWidth

  def drawableContentStyle(implicit appContext: AppContext): Tweak[FrameLayout] = {
    val size = resGetDimensionPixelSize(R.dimen.path_morphing_size_content_drawable)
    lp[LinearLayout](size, size) +
      llLayoutGravity(Gravity.CENTER)
  }


  def drawableStyle(width: Int, height: Int, stroke: Int)(implicit appContext: AppContext): Tweak[ImageView] =
    lp[LinearLayout](width, height) +
      flLayoutGravity(Gravity.CENTER) +
      vMargins(resGetDimensionPixelSize(R.dimen.padding_default)) +
      ivSrc(new PathMorphDrawable(defaultStroke = stroke)) +
      vBackground(R.drawable.background_item_square)

  def colorSelectorStyle(selected: Boolean = false)(implicit appContext: AppContext): Tweak[ImageView] = {
    val size = resGetDimensionPixelSize(R.dimen.path_morphing_size_circles)
    lp[LinearLayout](size, size) +
      vPaddings(8 dp) +
      vMargins(8 dp) +
      (selected match {
        case true => ivSrc(new PathMorphDrawable(defaultIcon = CHECK, defaultStroke = 2 dp))
        case _ => ivSrc(new PathMorphDrawable(defaultStroke = 2 dp))
      })
  }

  def iconSelectorStyle(icon: Int, selected: Boolean = false)(implicit appContext: AppContext): Tweak[ImageView] = {
    val size = resGetDimensionPixelSize(R.dimen.path_morphing_size_circles)
    lp[LinearLayout](size, size) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default)) +
      vBackground(R.drawable.background_item_icon_selector) +
      vMargins(resGetDimensionPixelSize(R.dimen.padding_default)) +
      ivSrc(new PathMorphDrawable(defaultIcon = icon, defaultStroke = 2 dp)) +
      vActivated(selected)
  }

  val tableLayoutStyle: Tweak[LinearLayout] =
    vMatchWidth +
      llVertical

  def tableLayoutRowStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    vMatchWidth +
      llHorizontal +
      llGravity(Gravity.CENTER)
}
