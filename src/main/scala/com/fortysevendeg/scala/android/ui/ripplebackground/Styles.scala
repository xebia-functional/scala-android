package com.fortysevendeg.scala.android.ui.ripplebackground

import android.view.Gravity
import android.widget.LinearLayout
import com.fortysevendeg.scala.android.macroid.LinearLayoutTweaks._
import com.fortysevendeg.scala.android.macroid.ViewTweaks._
import macroid.AppContext
import macroid.FullDsl._

object Styles {

  val rootStyle = vMatchParent + llVertical

  val contentStyle = vWrapContent

  val backgroundStyle = vMatchParent

  val circlesContentStyle = vMatchParent + llGravity(Gravity.CENTER_HORIZONTAL)

  def circleStyle(implicit appContext: AppContext) = lp[LinearLayout](64 dp, 64 dp) + vMargins(8 dp)

}
