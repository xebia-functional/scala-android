package com.fortysevendeg.scala.android.ui.apirequest

import android.support.v4.app.{Fragment, FragmentManager}
import android.widget._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
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

  def layoutView(implicit appContext: AppContext, 
    context: ActivityContext) =
    l[FrameLayout](
      w[ProgressBar] <~ wire(progressBar) <~ progressBarStyle,
      l[LinearLayout](
        w[TextView] <~ wire(errorText) <~ errorMessageStyle,
        w[Button] <~ wire(reloadButton) <~ errorButtonStyle
      ) <~ wire(errorContent) <~ errorContentStyle
    ) <~ errorLayoutStyle

}

class ForecastFragmentLayout(implicit appContext: AppContext, context: ActivityContext) extends ErrorLayout {

  var errorLayoutContent = slot[FrameLayout]
  
  var detailLayoutContent = slot[LinearLayout]

  var locationTextView = slot[TextView]

  var forecastImageView = slot[ImageView]

  var temperatureTextView = slot[TextView]

  val content = getUi(
    l[FrameLayout](
      layoutView <~ wire(errorLayoutContent),
      l[LinearLayout](
        l[LinearLayout](
          w[ImageView] <~ markerImageViewStyle,
          w[TextView] <~ wire(locationTextView) <~ locationTextViewStyle
        ) <~ forecastLocationLayoutStyle,
        l[LinearLayout](
          w[ImageView] <~ wire(forecastImageView) <~ forecastImageViewStyle,
          w[TextView] <~ wire(temperatureTextView) <~ temperatureTextViewStyle
        ) <~ forecastDetailLayoutStyle
      ) <~ wire(detailLayoutContent) <~ forecastLayoutStyle
    ) <~ forecastFragmentLayoutStyle
  )

  def layout = content

}

class AboutDialogLayout(implicit appContext: AppContext, context: ActivityContext) {

  var textView = slot[TextView]

  val content = getUi(
    l[FrameLayout](
      w[TextView] <~ wire(textView) <~ dialogTextViewStyle
    ) <~ aboutDialogLayoutStyle
  )

  def layout = content

}
