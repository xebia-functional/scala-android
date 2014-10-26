package com.fortysevendeg.scala.android.ui.main

import android.support.v7.widget.{CardView, RecyclerView}
import android.widget.{TextView, Button, LinearLayout}
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid.{IdGeneration, Tweak, ActivityContext, AppContext}
import Styles._

trait Layout extends ToolbarLayout {

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

class Adapter(implicit appContext: AppContext, context: ActivityContext) {

  var title = slot[TextView]

  var description = slot[TextView]

  var api = slot[TextView]

  val content = {
    getUi(
      l[CardView](
        l[LinearLayout](
          l[LinearLayout](
            w[TextView] <~ wire(title) <~ titleStyle,
            w[TextView] <~ wire(api) <~ apiStyle
          ) <~ itemTopStyle,
          w[TextView] <~ wire(description) <~ descriptionStyle
        ) <~ itemStyle
      ) <~ cardStyle
    )
  }

  def layout = content

}