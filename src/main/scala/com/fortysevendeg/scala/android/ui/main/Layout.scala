package com.fortysevendeg.scala.android.ui.main

import android.support.v7.widget.{CardView, RecyclerView}
import android.widget.{ImageView, LinearLayout, TextView}
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext}
import com.fortysevendeg.macroid.extras.TextTweaks._

trait Layout
  extends ToolbarLayout
  with Styles {

  var content = slot[LinearLayout]

  var recyclerView = slot[RecyclerView]

  def layout(implicit appContext: AppContext, context: ActivityContext) = {
    getUi(
      l[LinearLayout](
        toolBarLayout,
        w[RecyclerView] <~ wire(recyclerView) <~ listStyle
      ) <~ wire(content) <~ contentStyle
    )
  }

}

class Adapter(implicit appContext: AppContext, context: ActivityContext)
  extends AdapterStyles {

  var title = slot[TextView]

  var description = slot[TextView]

  var api = slot[TextView]

  var username = slot[TextView]

  var twitter = slot[TextView]

  var avatar = slot[ImageView]

  val content = {
    getUi(
      l[CardView](
        l[LinearLayout](
          l[LinearLayout](
            w[TextView] <~ wire(title) <~ titleStyle,
            w[TextView] <~ wire(api) <~ apiStyle
          ) <~ itemTopStyle,
          w[TextView] <~ wire(description) <~ descriptionStyle,
          w[ImageView] <~ lineHorizontalStyle,
          l[LinearLayout](
            l[LinearLayout](
              w[ImageView] <~ wire(avatar) <~ avatarStyle,
              l[LinearLayout](
                w[TextView] <~ wire(username) <~ userNameStyle,
                w[TextView] <~ wire(twitter) <~ twitterStyle
              ) <~ userNameContentStyle
            ) <~ bottomUserContentStyle,
            w[ImageView] <~ lineVerticalStyle,
            l[LinearLayout](
              l[LinearLayout](
                w[TextView] <~ levelStyle <~ tvText(R.string.scala_level),
                w[TextView] <~ levelNumberStyle <~ tvText("1"),
                w[TextView] <~ levelNumberStyle <~ tvText("2"),
                w[TextView] <~ levelNumberStyle <~ tvText("3")
              ),
              l[LinearLayout](
                w[TextView] <~ levelStyle <~ tvText(R.string.android_level),
                w[TextView] <~ levelNumberStyle <~ tvText("1"),
                w[TextView] <~ levelNumberStyle <~ tvText("2"),
                w[TextView] <~ levelNumberStyle <~ tvText("3")
              )
            ) <~ bottomLevelsContentStyle
          ) <~ bottomContentStyle
        ) <~ itemStyle
      ) <~ cardStyle
    )
  }

  def layout = content

}