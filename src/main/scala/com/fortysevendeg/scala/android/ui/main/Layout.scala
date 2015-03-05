package com.fortysevendeg.scala.android.ui.main

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.{CardView, RecyclerView}
import android.widget.{ImageView, LinearLayout, TextView}
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid.{Ui, ActivityContext, AppContext}
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._

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

  var androidLevel = slot[TextView]

  var scalaLevel = slot[TextView]

  var userContent = slot[LinearLayout]

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
            ) <~ bottomUserContentStyle <~ wire(userContent) <~ On.click {
              Ui {
                for {
                  content <- userContent
                  tag <- Option(content.getTag)
                } yield {
                  context.get.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(resGetString(R.string.url_twitter_user, tag.toString))))
                }
              }
            },
            w[ImageView] <~ lineVerticalStyle,
            l[LinearLayout](
              l[LinearLayout](
                w[TextView] <~ levelStyle <~ tvText(R.string.scala_level),
                w[TextView] <~ levelTypeStyle <~ wire(scalaLevel)
              ) <~ levelItemContentStyle,
              l[LinearLayout](
                w[TextView] <~ levelStyle <~ tvText(R.string.android_level),
                w[TextView] <~ levelTypeStyle <~ wire(androidLevel)
              )
            ) <~ bottomLevelsContentStyle
          ) <~ bottomContentStyle
        ) <~ itemStyle
      ) <~ cardStyle
    )
  }

  def layout = content

}