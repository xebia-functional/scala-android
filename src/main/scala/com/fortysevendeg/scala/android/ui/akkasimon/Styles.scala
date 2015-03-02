package com.fortysevendeg.scala.android.ui.akkasimon

import android.graphics.Color
import android.view.Gravity
import android.widget.{TextView, Button, FrameLayout, LinearLayout}
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import macroid.{Tweak, AppContext}

import scala.language.postfixOps

object Styles {

  def simonButton(color: Int, alpha: Float = 0.3f)(implicit appCtx: AppContext) =
    vBackgroundColor(color) +
      vAlpha(alpha)

  val rootStyle: Tweak[LinearLayout] =
    llMatchWeightVertical +
      llVertical

  val contentStyle: Tweak[FrameLayout] = llMatchWeightVertical

  val optionsContentStyle: Tweak[LinearLayout] =
    vMatchParent +
      llGravity(Gravity.CENTER)

  val buttonsStyle: Tweak[Button] =
    vWrapContent +
      tvText(R.string.simon_start)

  val gameContentStyle: Tweak[LinearLayout] =
    vMatchParent +
      llVertical +
      vGone

  val roundsStyle: Tweak[TextView] =
    vMatchWidth +
      tvGravity(Gravity.CENTER)

  val simonContainerStyle: Tweak[LinearLayout] =
    llMatchWeightVertical +
      llHorizontal

  val columnStyle: Tweak[LinearLayout] =
    llMatchWeightHorizontal +
      llVertical

  val rowStyle: Tweak[FrameLayout] = llMatchWeightVertical

}
