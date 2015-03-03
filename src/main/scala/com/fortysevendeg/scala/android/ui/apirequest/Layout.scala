package com.fortysevendeg.scala.android.ui.apirequest

import android.support.v4.app.{Fragment, FragmentManager}
import android.widget._
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.apirequest.Styles._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext, FragmentManagerContext, IdGeneration}

trait Layout extends ToolbarLayout with IdGeneration {
  
  val loaderFragmentName = Tag.fragmentLoader
  
  val forecastFragmentName = Tag.fragmentForecast
  
  var content = slot[FrameLayout]

  def layout(implicit appContext: AppContext,
    context: ActivityContext,
    managerContext: FragmentManagerContext[Fragment, FragmentManager]) = getUi(
    l[LinearLayout](
      toolBarLayout <~ tbTitle(R.string.title_forecast_api_request),
      l[FrameLayout]() <~ wire(content) <~ id(Id.fragment) <~ contentStyle
      ) <~ rootStyle
    )

}

trait ErrorLayout {

  var progressBar = slot[ProgressBar]

  var errorContent = slot[LinearLayout]

  var reloadButton = slot[Button]

  var errorText = slot[TextView]

  def layoutView(implicit appContext: AppContext, context: ActivityContext) = {
    l[FrameLayout](
      w[ProgressBar] <~ wire(progressBar) <~ progressBarStyle,
      l[LinearLayout](
        w[TextView] <~ errorMessageStyle <~ wire(errorText),
        w[Button] <~ errorButtonStyle <~ wire(reloadButton)
      ) <~ wire(errorContent) <~ errorContentStyle
    ) <~ errorLayoutStyle
  }
  
  def loading() =
    runUi(
      (progressBar <~ vVisible) ~
        (errorContent <~ vGone))

  def error(errorMessage: Option[Int]) =
    runUi(
      (progressBar <~ vGone) ~
        (errorContent <~ vVisible) ~
        (errorText <~ tvText(errorMessage getOrElse R.string.error_message_api_request_default))
    )

}

class LoaderFragmentLayout(implicit appContext: AppContext, context: ActivityContext) extends ErrorLayout {

  val content = getUi(layoutView)

  def layout = content

}

class ForecastFragmentLayout(implicit appContext: AppContext, context: ActivityContext) extends ErrorLayout {

  var errorLayoutContent = slot[FrameLayout]
  
  var detailLayoutContent = slot[FrameLayout]

  var textView = slot[TextView]

  val content = getUi(
    l[FrameLayout](
      layoutView <~ wire(errorLayoutContent) <~ forecastErrorLayoutStyle,
      l[FrameLayout](
        w[TextView] <~ wire(textView) <~ textViewStyle
      ) <~ wire(detailLayoutContent) <~ forecastDetailLayoutStyle
    ) <~ forecastLayoutStyle
  )

  def layout = content

}
