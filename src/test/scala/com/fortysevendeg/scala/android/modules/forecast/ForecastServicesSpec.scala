package com.fortysevendeg.scala.android.modules.forecast

import com.fortysevendeg.scala.android.modules.forecast.impl.ForecastServices
import com.fortysevendeg.scala.android.ui.apirequest.service.model._
import com.fortysevendeg.scala.android.{AppContextTestSupport, BaseTestSupport}
import com.squareup.okhttp.OkHttpClient
import io.taig.communicator.result.Parser
import macroid.AppContext
import org.specs2.mutable.Specification

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

trait ForecastServiceMock
  extends ForecastServices
  with AppContextTestSupport {

  override def loadJsonUrl(latitude: Double, longitude: Double)(implicit appContextProvider: AppContext): String = "http://fake_url/"

  override def loadHeaderTuple(implicit appContextProvider: AppContext): (String, String) = ("key_name", "key_value")

}

class ForecastServicesSpec
  extends Specification
  with BaseTestSupport {
  
  val validJson: ApiModel = ApiModel(
    id = 5809844,
    dt = 1425060066,
    base = "cmc stations",
    cod = 200,
    name = "Seattle",
    coord = ApiCoord(-122.38, 47.66),
    sys = ApiSys(message = 0.0168, country = "US", sunrise = 1425048757, sunset = 1425088297),
    main = ApiMain(temp = 6.636, temp_min = 6.636, temp_max = 6.636, pressure = 1009.8, sea_level = 1021.71, grnd_level = 1009.8, humidity = 100),
    weather = Seq(ApiWeather(id = 501, main = "Rain", description = "moderate rain", icon = "10d")),
    wind = ApiWind(speed = 2.17, deg = 36.0011),
    rain = Some(Map("3h" -> 3.5)),
    clouds = ApiClouds(all = 92))
  
  "ForecastServices component" should {
    
    "return forecast with right JSON" in
      new ForecastServiceMock {

        override def loadJson[T](url: String, headers: Seq[(String, String)])(implicit parser: Parser[T], client: OkHttpClient = new OkHttpClient()): Future[T] =
          Future.successful[T](validJson.asInstanceOf[T])
        
        val forecast = Forecast(
          Location(5809844, "Seattle", 47.66, -122.38),
          Some(Weather(501, "Rain", "moderate rain", "10d", 6.636)))

        Await.result(loadForecast(ForecastRequest(0, 0)), Duration.Inf) shouldEqual ForecastResponse(Some(forecast))
      }

    "got the exception thrown by the call to loadJson" in
      new ForecastServiceMock {

        override def loadJson[T](url: String, headers: Seq[(String, String)])(implicit parser: Parser[T], client: OkHttpClient = new OkHttpClient()): Future[T] =
          Future.failed[T](new RuntimeException())

        Await.result(loadForecast(ForecastRequest(0, 0)), Duration.Inf) must throwA[RuntimeException]
      }

  }

}
