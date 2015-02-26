package com.fortysevendeg.scala.android.ui.textstyles

import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.AppContext
import scala.language.postfixOps

object Styles {

  val contentStyle = llVertical

  def scrollStyle(implicit appContext: AppContext) =
    llVertical +
      vPaddings(8 dp)

  val textLargeStyle = tvSize(24)

  val textMediumStyle = tvSize(18)

  val textSmallStyle = tvSize(12)

  val textLightLargeStyle =
    tvSize(24) +
      tvNormalLight

  val textLightMediumStyle =
    tvSize(18) +
      tvNormalLight

  val textLightSmallStyle =
    tvSize(12) +
      tvNormalLight

  val textBoldCondensedLargeStyle =
    tvSize(24) +
      tvBoldCondensed

  val textBoldCondensedMediumStyle =
    tvSize(18) +
      tvBoldCondensed

  val textBoldCondensedSmallStyle =
    tvSize(12) +
      tvBoldCondensed

  val textItalicLargeStyle =
    tvSize(24) +
      tvItalic

  val textItalicMediumStyle =
    tvSize(18) +
      tvItalic

  val textItalicSmallStyle =
    tvSize(12) +
      tvItalic

}
