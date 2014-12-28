package com.fortysevendeg.scala.android.ui.circularreveal

import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.ui.components.CircleButtonTweaks._
import macroid.AppContext
import macroid.FullDsl._
import scala.language.postfixOps

object Styles {

  val rootStyle = llVertical

  val contentStyle = vMatchParent + vPaddings(24)

  def fabStyle(implicit appContext: AppContext) =
    lp[FrameLayout](64 dp, 64 dp) + cbDrawable(R.drawable.ic_add) + flLayoutGravity(Gravity.RIGHT | Gravity.BOTTOM)

  // Fragment Styles

  val contentRevealStyle = vMatchParent + vBackgroundColor(Color.GRAY) + llGravity(Gravity.CENTER)

  val textStyle = tvText(R.string.exampleText) + tvSize(24) + tvColor(Color.RED)

}
