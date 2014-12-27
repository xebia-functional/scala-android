package com.fortysevendeg.scala.android.ui.circularreveal

import android.widget.{TextView, Button, FrameLayout, LinearLayout}
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.scala.android.ui.circularreveal.Styles._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import com.fortysevendeg.scala.android.ui.components.CircleButton
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext}

trait Layout extends ToolbarLayout {

  var circleButton = slot[CircleButton]

  var content = slot[LinearLayout]

  def layout(implicit appContext: AppContext, context: ActivityContext) = {
    getUi(
      l[LinearLayout](
        toolBarLayout <~ tbTitle(R.string.title_circular_reveal_styles),
        l[FrameLayout](
          w[CircleButton] <~ wire(circleButton) <~ fabStyle,
          l[LinearLayout] (
            w[TextView] <~ textStyle
          ) <~ wire(content) <~ contentRevealStyle
        ) <~ contentStyle
      ) <~ rootStyle
    )
  }

}
