package com.fortysevendeg.scala.android.ui.pathmorphing

import android.view.Gravity
import com.fortysevendeg.scala.android.macroid.LinearLayoutTweaks._
import com.fortysevendeg.scala.android.macroid.FrameLayoutTweaks._
import com.fortysevendeg.scala.android.macroid.ViewTweaks._
import com.fortysevendeg.scala.android.macroid.ImageViewTweaks._
import macroid.AppContext
import macroid.FullDsl._
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawable

object Styles {

  val rootStyle = vMatchParent + llVertical

  val contentStyle = vMatchParent

  val contentRadiosStyle = vMatchWidth

  val contentItemsRadiosStyle = llMatchWeightHorizontal

  val radioGroupStyle = flLayoutGravity(Gravity.CENTER_HORIZONTAL)

  def radioItemStyle(implicit appContext: AppContext) = vPaddings(8 dp)

  def drawableStyle(implicit appContext: AppContext) = vContentSize(48 dp, 48 dp) + flLayoutGravity(Gravity.CENTER) + ivSrc(new PathMorphDrawable())

}
