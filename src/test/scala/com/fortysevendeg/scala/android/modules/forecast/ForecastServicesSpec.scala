package com.fortysevendeg.scala.android.modules.forecast

import com.fortysevendeg.scala.android.modules.forecast.impl.ForecastServices
import com.fortysevendeg.scala.android.ui.apirequest.service.model.{Forecast, Location, Weather}
import com.fortysevendeg.scala.android.{AppContextTestSupport, BaseTestSupport}
import macroid.AppContext
import org.specs2.mutable.Specification

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}

trait ForecastServiceSupport
  extends ForecastServices
  with AppContextTestSupport {

  override def loadJsonUrl(latitude: Double, longitude: Double)(implicit appContextProvider: AppContext): String = "http://api.openweathermap.org/"

  override def loadHeaderTuple(implicit appContextProvider: AppContext): (String, String) = ("key_name", "key_value")

}

class ForecastServicesSpec
  extends Specification
  with BaseTestSupport {
  
  val validJson =
    """
      |{
      |  "coord": {
      |    "lon": -122.38,
      |    "lat": 47.66
      |  },
      |  "sys": {
      |    "message": 0.0168,
      |    "country": "US",
      |    "sunrise": 1425048757,
      |    "sunset": 1425088297
      |  },
      |  "weather": [
      |    {
      |      "id": 501,
      |      "main": "Rain",
      |      "description": "moderate rain",
      |      "icon": "10d"
      |    }
      |  ],
      |  "base": "cmc stations",
      |  "main": {
      |    "temp": 6.636,
      |    "temp_min": 6.636,
      |    "temp_max": 6.636,
      |    "pressure": 1009.8,
      |    "sea_level": 1021.74,
      |    "grnd_level": 1009.8,
      |    "humidity": 100
      |  },
      |  "wind": {
      |    "speed": 2.17,
      |    "deg": 36.0011
      |  },
      |  "clouds": {
      |    "all": 92
      |  },
      |  "rain": {
      |    "3h": 3.5
      |  },
      |  "dt": 1425060066,
      |  "id": 5809844,
      |  "name": "Seattle",
      |  "cod": 200
      |}
    """.stripMargin
  
  val wrongJson = """{"message": "hello world" }"""

  val invalidJson = "this is not a JSON"
  
  "ForecastServices component" should {
    
    "return forecast with right JSON" in
      new ForecastServiceSupport {
        
        override def getJson(url: String, headers: Seq[(String, String)] = Seq.empty): Try[String] = {
          Success(validJson)
        }
        
        val forecast = Forecast(
          Location(5809844, "Seattle", 47.66, -122.38),
          Some(Weather(501, "Rain", "moderate rain", "10d", 6.636)))

        Await.result(loadForecast(ForecastRequest(0, 0)), Duration.Inf) == ForecastResponse(Some(forecast))
      }

    "return None with a wrong JSON" in
      new ForecastServiceSupport {

        override def getJson(url: String, headers: Seq[(String, String)] = Seq.empty): Try[String] = {
          Success(wrongJson)
        }

        Await.result(loadForecast(ForecastRequest(0, 0)), Duration.Inf) == ForecastResponse(None)
      }

    "return None with a invalid JSON" in
      new ForecastServiceSupport {

        override def getJson(url: String, headers: Seq[(String, String)] = Seq.empty): Try[String] = {
          Success(invalidJson)
        }

        Await.result(loadForecast(ForecastRequest(0, 0)), Duration.Inf) == ForecastResponse(None)
      }

    "return None with a failed call to getJson" in
      new ForecastServiceSupport {

        override def getJson(url: String, headers: Seq[(String, String)] = Seq.empty): Try[String] = {
          Failure(new RuntimeException)
        }

        Await.result(loadForecast(ForecastRequest(0, 0)), Duration.Inf) == ForecastResponse(None)
      }

  }

}
