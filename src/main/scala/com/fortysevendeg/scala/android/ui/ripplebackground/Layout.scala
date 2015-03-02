package com.fortysevendeg.scala.android.ui.ripplebackground

import android.widget.{FrameLayout, LinearLayout}
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import com.fortysevendeg.scala.android.ui.components.{CircleView, RippleBackgroundView}
import Styles._
import macroid.{ActivityContext, AppContext}
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._

trait Layout extends ToolbarLayout {

  var rippleBackground = slot[RippleBackgroundView]

  var circle1 = slot[CircleView]

  var circle2 = slot[CircleView]

  var circle3 = slot[CircleView]

  def layout(implicit appContext: AppContext, context: ActivityContext) = getUi(
    l[LinearLayout](
      toolBarLayout <~ tbTitle(R.string.title_ripple_background),
      l[FrameLayout](
        w[RippleBackgroundView] <~ wire(rippleBackground) <~ backgroundStyle,
        l[LinearLayout](
          w[CircleView] <~ wire(circle1) <~ circleStyle,
          w[CircleView] <~ wire(circle2) <~ circleStyle,
          w[CircleView] <~ wire(circle3) <~ circleStyle
        ) <~ circlesContentStyle
      ) <~ colorContent
    ) <~ rootStyle
  )

}
