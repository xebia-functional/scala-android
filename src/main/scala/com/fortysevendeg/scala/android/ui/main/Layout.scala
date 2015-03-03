package com.fortysevendeg.scala.android.ui.main

import android.support.v7.widget.{CardView, RecyclerView}
import android.widget.{LinearLayout, TextView}
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext}

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