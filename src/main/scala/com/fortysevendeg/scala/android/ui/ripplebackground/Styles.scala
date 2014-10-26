package com.fortysevendeg.scala.android.ui.ripplebackground

import android.view.Gravity
import android.widget.LinearLayout
import com.fortysevendeg.scala.android.macroid.LinearLayoutTweaks._
import com.fortysevendeg.scala.android.macroid.ViewTweaks._
import macroid.FullDsl._

object Styles {

  val rootStyle = vMatchParent + llVertical

  val contentStyle = vWrapContent

  val backgroundStyle = vMatchParent

  val circlesContentStyle = vMatchWidth + llGravity(Gravity.CENTER_HORIZONTAL)

  // TODO We need use dp, px or ps in this object. Compiler say "value dp is not a member of Int"
  // It's possible that we need implicits parameters
  //val circleStyle = lp[LinearLayout](60 dp, 60 dp)

  val circleStyle = lp[LinearLayout](100, 100) + vMargins(20)

}
