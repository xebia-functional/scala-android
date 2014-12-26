package com.fortysevendeg.scala.android.ui.pathmorphing

import android.view.Gravity
import android.widget.LinearLayout
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.SeekBarTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.ui.components.{PathMorphDrawable, TypeIcons}
import macroid.AppContext
import macroid.FullDsl._

object Styles {

  val rootStyle = vMatchParent + llVertical

  val contentStyle = vMatchParent + llVertical

  def scrollViewStyle(implicit appContext: AppContext) = vMatchParent + vPaddings(8 dp)

  val horizontalLinearLayoutStyle = llHorizontal

  val verticalLinearLayoutStyle = vMatchParent + llVertical

  val contentItemsRadiosStyle = llMatchWeightHorizontal

  val colorSelectorLayoutStyle = vMatchWidth

  val titleStyle = tvNormalLight

  val strokeStyle = sbMax(4) + sbProgress(0) + vMatchWidth

  val sizeStyle = sbMax(3) + sbProgress(0) + vMatchWidth

  def drawableStyle(width: Int, height: Int, stroke: Int)(implicit appContext: AppContext) =
    lp[LinearLayout](width, height) +
        llLayoutGravity(Gravity.CENTER) +
        vMargins(8 dp) +
        ivSrc(new PathMorphDrawable(defaultStroke = stroke)) +
        vBackground(R.drawable.background_item_square)

  def colorSelectorStyle(selected: Boolean = false)(implicit appContext: AppContext) =
    lp[LinearLayout](48 dp, 48 dp) +
        vPaddings(8 dp) +
        vMargins(8 dp) +
        (selected match {
          case true => ivSrc(new PathMorphDrawable(defaultIcon = TypeIcons.CHECK, defaultStroke = 2 dp))
          case _ => ivSrc(new PathMorphDrawable(defaultStroke = 2 dp))
        })

  def iconSelectorStyle(icon: Int, selected: Boolean = false)(implicit appContext: AppContext) =
    lp[LinearLayout](48 dp, 48 dp) +
        vPaddings(8 dp) +
        vBackground(R.drawable.background_item_icon_selector) +
        vMargins(8 dp) +
        ivSrc(new PathMorphDrawable(defaultIcon = icon, defaultStroke = 2 dp)) +
        vActivated(selected)

  val tableLayoutStyle = vMatchWidth + llVertical

  def tableLayoutRowStyle(implicit appContext: AppContext) = vMatchWidth + llHorizontal + llGravity(Gravity.CENTER)
}
