package com.fortysevendeg.scala.android.modules.forecast

import com.fortysevendeg.scala.android.modules.forecast.impl.ForecastServicesComponentImpl
import com.fortysevendeg.scala.android.ui.apirequest.service.model.{Forecast, Location, Weather}
import com.fortysevendeg.scala.android.utils.TestUtils._
import com.fortysevendeg.scala.android.{AppContextTestSupport, BaseTestSupport}
import org.specs2.mutable.Specification

import scala.util.{Success, Try}

trait ForecastServicesComponentSupport
  extends ForecastServicesComponentImpl
  with AppContextTestSupport {

}

class ForecastServicesComponentSpec
  extends Specification
  with BaseTestSupport {
  
  val validJson =
    """
      |{
      |  "coord": {
      |    "lon": -6.2,
      |    "lat": 36.46
      |  },
      |  "sys": {
      |    "message": 0.1088,
      |    "country": "ES",
      |    "sunrise": 1425020249,
      |    "sunset": 1425061046
      |  },
      |  "weather": [
      |    {
      |      "id": 800,
      |      "main": "Clear",
      |      "description": "Sky is Clear",
      |      "icon": "01d"
      |    }
      |  ],
      |  "base": "cmc stations",
      |  "main": {
      |    "temp": 15.486,
      |    "temp_min": 15.486,
      |    "temp_max": 15.486,
      |    "pressure": 1037.84,
      |    "sea_level": 1038.36,
      |    "grnd_level": 1037.84,
      |    "humidity": 92
      |  },
      |  "wind": {
      |    "speed": 6.35,
      |    "deg": 264.501
      |  },
      |  "clouds": {
      |    "all": 0
      |  },
      |  "dt": 1425060205,
      |  "id": 2512169,
      |  "name": "Puerto Real",
      |  "cod": 200
      |}
    """.stripMargin
  
  val wrongJson = """{"message": "hello world" }"""

  val invalidJson = "this is not a JSON"
  
  "ForecastServices component" should {
    
    "return forecast with right JSON" in
      new ForecastServicesComponentSupport {
        
        override def getJson(url: String, headers: Seq[(String, String)] = Seq.empty): Try[String] = {
          Success(validJson)
        }
        
        val forecast = Forecast(
          Location(2512169, "Puerto Real", 36.46, -6.2),
          Some(Weather(800, "Clear", "Sky is Clear", "01d", 15.486)))
        
        forecastServices.loadForecast(ForecastRequest(0, 0)) *=== ForecastResponse(Some(forecast))
      }

    "return None with a wrong JSON" in
      new ForecastServicesComponentSupport {

        override def getJson(url: String, headers: Seq[(String, String)] = Seq.empty): Try[String] = {
          Success(wrongJson)
        }

        forecastServices.loadForecast(ForecastRequest(0, 0)) *=== ForecastResponse(None)
      }

    "return None with a invalid JSON" in
      new ForecastServicesComponentSupport {

        override def getJson(url: String, headers: Seq[(String, String)] = Seq.empty): Try[String] = {
          Success(invalidJson)
        }

        forecastServices.loadForecast(ForecastRequest(0, 0)) *=== ForecastResponse(None)
      }

  }

}
