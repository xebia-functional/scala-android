package com.fortysevendeg.scala.android.ui.main

import android.graphics.Color
import android.view.Gravity
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.macroid.LinearLayoutTweaks._
import com.fortysevendeg.scala.android.macroid.RecyclerViewTweaks._
import com.fortysevendeg.scala.android.macroid.TextTweaks._
import com.fortysevendeg.scala.android.macroid.ViewTweaks._
import com.fortysevendeg.scala.android.macroid.CardViewTweaks._

object Styles {

  val listStyle = llMatchWeightVertical

  val contentStyle = llVertical

  val cardStyle = vMatchWidth

  val itemStyle = llVertical + vMatchWidth + vPaddings(40)

  val itemTopStyle = llHorizontal + vContentSizeMatchWidth(100) + llGravity(Gravity.CENTER_VERTICAL)

  val titleStyle = llWrapWeightHorizontal + tvSize(18) + tvColor(Color.BLACK)

  val descriptionStyle = tvSize(14) + tvNormalLight + tvColor(Color.GRAY) + tvMaxLines(2)

  val apiStyle = tvSize(10) + tvItalicLight + tvColor(Color.WHITE) + vPaddings(12) + vBackground(R.drawable.background_item_api)

}
