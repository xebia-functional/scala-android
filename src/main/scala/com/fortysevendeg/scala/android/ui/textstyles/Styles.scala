package com.fortysevendeg.scala.android.ui.textstyles

import com.fortysevendeg.scala.android.R
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.AppContext
import scala.language.postfixOps

trait Styles {

  val contentStyle = llVertical

  def scrollStyle(implicit appContext: AppContext) =
    llVertical +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default_large))

  def textLargeStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_large) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textMediumStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_medium) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textSmallStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_small) +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textLightLargeStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_large) +
      tvNormalLight +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textLightMediumStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_medium) +
      tvNormalLight +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textLightSmallStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_small) +
      tvNormalLight +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textBoldCondensedLargeStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_large) +
      tvBoldCondensed +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textBoldCondensedMediumStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_medium) +
      tvBoldCondensed +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textBoldCondensedSmallStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_small) +
      tvBoldCondensed +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textItalicLargeStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_large) +
      tvItalic +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textItalicMediumStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_medium) +
      tvItalic +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

  def textItalicSmallStyle(implicit appContext: AppContext) =
    tvSizeResource(R.dimen.font_size_small) +
      tvItalic +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default))

}
