package com.fortysevendeg.scala.android.ui.apirequest

import java.text.DecimalFormat

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{LayoutInflater, View, ViewGroup}
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.modules.forecast.ForecastRequest
import com.fortysevendeg.scala.android.modules.forecast.impl.ForecastServices
import com.fortysevendeg.scala.android.modules.forecast.model._
import macroid.FullDsl._
import macroid.{AppContext, Contexts, Ui}

import scala.concurrent.ExecutionContext.Implicits.global

class ForecastFragment 
  extends Fragment 
  with Contexts[Fragment] {

  implicit lazy val appContextProvider: AppContext = fragmentAppContext

  private var fragmentLayout: Option[ForecastFragmentLayout] = None
  
  val decimalFormatter = new DecimalFormat("#.##'°'")
  
  val resourceName = "forecast_%s"
  
  val placeholderTemperature = "-°"
  
  val iconSizeIdentifier = 2

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {

    val fLayout = new ForecastFragmentLayout

    fragmentLayout = Some(fLayout)
    
    fLayout.reloadButton <~ On.click(Ui { reload })

    fLayout.layout
  }

  override def onViewCreated(view: View, savedInstanceState: Bundle) = {
    super.onViewCreated(view, savedInstanceState)
    reload
  }
  
  def reload = {
    loading
    Option(getActivity) map (_.asInstanceOf[ForecastApiRequestActivity].loadClientLocation)
  }


  def loadForecast(location: (Double, Double)) = {
    val result = for {
      forecast <- ForecastServices.loadForecast(ForecastRequest(location._1, location._2))
    } yield forecast.forecastMaybe

    result map {
      case Some(forecast) => showForecast(forecast)
      case _ => error(Some(R.string.error_message_api_request_loading))
    } recover {
      case _ => error(Some(R.string.error_message_api_request_loading))
    }
  }

  def showForecast(forecast: Forecast) =
    fragmentLayout map { layout =>
      runUi(
        (layout.progressBar <~ vGone) ~
          (layout.errorContent <~ vGone) ~
          (layout.detailLayoutContent <~ vVisible) ~
          (layout.locationTextView <~ tvText(forecast.location.name)) ~
          (layout.forecastImageView <~ ivSrc(loadWeatherIcon(forecast.weather))) ~
          (layout.temperatureTextView <~ tvText(loadWeatherTemperature(forecast.weather)))
      )
    }

  def loadWeatherIcon(weatherMaybe: Option[Weather]): Drawable = {
    val result = weatherMaybe flatMap { weather =>
      Option(weather.icon) match {
        case Some(icon) if icon.length >= iconSizeIdentifier => resGetDrawable(String.format(resourceName, icon.substring(0, iconSizeIdentifier)))
        case _ => Option(resGetDrawable(R.drawable.unknown))
      }
    }
    result getOrElse resGetDrawable(R.drawable.unknown)
  }

  def loadWeatherTemperature(weatherMaybe: Option[Weather]): String = {
    (for {
      weather <- weatherMaybe
      temperature <- weather.temperature      
    } yield decimalFormatter.format(temperature)) getOrElse placeholderTemperature
  }
  
  def loading =
    fragmentLayout map { layout =>
      runUi(
        (layout.progressBar <~ vVisible) ~
          (layout.errorContent <~ vGone) ~
          (layout.detailLayoutContent <~ vGone)
      )
    }
  
  def error(errorMessage: Option[Int]) =
    fragmentLayout map { layout =>
      runUi(
        (layout.progressBar <~ vGone) ~
          (layout.errorContent <~ vVisible) ~
          (layout.errorText <~ tvText(errorMessage getOrElse R.string.error_message_api_request_default)) ~
          (layout.detailLayoutContent <~ vGone)
      )
    }

}