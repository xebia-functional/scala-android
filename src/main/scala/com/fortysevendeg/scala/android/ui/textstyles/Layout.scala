package com.fortysevendeg.scala.android.ui.textstyles

import android.widget.{LinearLayout, ScrollView, TextView}
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import com.fortysevendeg.scala.android.ui.textstyles.Styles._
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext}

trait Layout extends ToolbarLayout {

  def layout(implicit appContext: AppContext, context: ActivityContext) = {
    getUi(
      l[LinearLayout](
        toolBarLayout <~ tbTitle(R.string.title_text_styles),
        l[ScrollView](
          l[LinearLayout](
            w[TextView] <~ textLargeStyle <~ text("Text Large"),
            w[TextView] <~ textMediumStyle <~ text("Text Medium"),
            w[TextView] <~ textSmallStyle <~ text("Text Small"),
            w[TextView] <~ textLightLargeStyle <~ text("Text Light Large"),
            w[TextView] <~ textLightMediumStyle <~ text("Text Light Medium"),
            w[TextView] <~ textLightSmallStyle <~ text("Text Light Small"),
            w[TextView] <~ textBoldCondensedLargeStyle <~ text("Text Bold Condensed Large"),
            w[TextView] <~ textBoldCondensedMediumStyle <~ text("Text Bold Condensed Medium"),
            w[TextView] <~ textBoldCondensedSmallStyle <~ text("Text Bold Condensed Small"),
            w[TextView] <~ textItalicLargeStyle <~ text("Text Italic Large"),
            w[TextView] <~ textItalicMediumStyle <~ text("Text Italic Medium"),
            w[TextView] <~ textItalicSmallStyle <~ text("Text Italic Small")
          ) <~ scrollStyle
        )
      ) <~ contentStyle
    )
  }

}
