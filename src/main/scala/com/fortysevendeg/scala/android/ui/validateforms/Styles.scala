package com.fortysevendeg.scala.android.ui.validateforms

import android.view.Gravity
import android.view.ViewGroup.LayoutParams._
import android.widget._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ThemeExtras._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import macroid.{ActivityContext, Tweak, AppContext}
import macroid.FullDsl._

import scala.language.postfixOps

trait Styles {

  val contentStyle = llVertical

  def scrollStyle(implicit appContext: AppContext): Tweak[ScrollView] =
    llMatchWeightVertical

  def scrollContentStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    llVertical +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default_large))

  def textLabelStyle(implicit appContext: AppContext): Tweak[TextView] =
    tvSizeResource(R.dimen.font_size_large) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default)) +
      tvNormalLight +
      tvSizeResource(R.dimen.font_size_normal)

  def editTextStyle(implicit appContext: AppContext): Tweak[EditText] =
    vMatchWidth

  def lineHorizontalStyle(implicit appContext: AppContext): Tweak[ImageView] =
    lp[LinearLayout](MATCH_PARENT, resGetDimensionPixelSize(R.dimen.line)) +
      vBackgroundColorResource(R.color.validate_line)

  def saveButtonStyle(implicit appContext: AppContext, activityContext: ActivityContext): Tweak[Button] =
    vMatchWidth +
      tvText(R.string.validate_save) +
      tvGravity(Gravity.CENTER) +
      vBackground(themeGetDrawable(android.R.attr.selectableItemBackground))

}
