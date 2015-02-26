package com.fortysevendeg.scala.android.ui.ripplebackground

import android.view.Gravity
import android.widget.LinearLayout
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.AppContext
import macroid.FullDsl._
import scala.language.postfixOps

object Styles {

  val rootStyle =
    vMatchParent +
      llVertical

  val contentStyle = vWrapContent

  val backgroundStyle = vMatchParent

  val circlesContentStyle =
    vMatchParent +
      llGravity(Gravity.CENTER_HORIZONTAL)

  def circleStyle(implicit appContext: AppContext) =
    lp[LinearLayout](64 dp, 64 dp) +
      vMargins(8 dp)

}
