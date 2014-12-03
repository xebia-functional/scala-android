package com.fortysevendeg.scala.android.ui.circularreveal

import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.macroid.FrameLayoutTweaks._
import com.fortysevendeg.scala.android.macroid.TextTweaks._
import com.fortysevendeg.scala.android.macroid.LinearLayoutTweaks._
import com.fortysevendeg.scala.android.macroid.ViewTweaks._
import com.fortysevendeg.scala.android.ui.components.CircleButtonTweaks._
import macroid.FullDsl._

object Styles {

  val rootStyle = llVertical

  val contentStyle = vMatchParent + vPaddings(24)

  val fabStyle = lp[FrameLayout](140, 140) + cbDrawable(R.drawable.ic_add) + flLayoutGravity(Gravity.RIGHT | Gravity.BOTTOM)

  val contentRevealStyle = vMatchParent + vBackgroundColor(Color.GRAY) + vInvisible + llGravity(Gravity.CENTER)

  val textStyle = tvText("Hi guys!") + tvSize(24) + tvColor(Color.WHITE)

}
