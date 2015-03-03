package com.fortysevendeg.scala.android.ui.apirequest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{LayoutInflater, View, ViewGroup}
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.modules.ComponentRegistryImpl
import com.fortysevendeg.scala.android.modules.forecast.ForecastRequest
import com.fortysevendeg.scala.android.ui.apirequest.service.model.Forecast
import macroid.{Ui, AppContext, Contexts}
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.ViewTweaks._

import scala.concurrent.ExecutionContext.Implicits.global

class ForecastFragment 
  extends Fragment 
  with Contexts[Fragment]
  with ComponentRegistryImpl {

  override implicit lazy val appContextProvider: AppContext = fragmentAppContext

  private var fragmentLayout: Option[ForecastFragmentLayout] = None

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {

    val fLayout = new ForecastFragmentLayout

    fragmentLayout = Some(fLayout)

    fLayout.reloadButton <~ On.click(Ui {
      loading
      Option(getActivity) map (_.asInstanceOf[ForecastApiRequestActivity].loadClientLocation)
    })

    fLayout.layout
  }

  override def onViewCreated(view: View, savedInstanceState: Bundle) = {
    super.onViewCreated(view, savedInstanceState)
    loading
  }

  def loadForecast(location: (Double, Double)) = {
    val result = for {
      forecast <- forecastServices.loadForecast(ForecastRequest(location._1, location._2))
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
          (layout.textView <~ tvText(s"${forecast.location.name} - ${forecast.weather.get.description}"))
      )
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