package com.fortysevendeg.scala.android.modules.forecast

import com.fortysevendeg.scala.android.modules.forecast.impl.ForecastServices
import com.fortysevendeg.scala.android.modules.forecast.model._
import com.fortysevendeg.scala.android.{ContextWrapperContextTestSupport, BaseTestSpecification}
import com.squareup.okhttp.OkHttpClient
import io.taig.communicator.result.Parser
import macroid.ContextWrapper

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

trait ForecastServiceSpecification
  extends BaseTestSpecification
  with ContextWrapperContextTestSupport {

  trait ForecastServiceScope
    extends BaseTestScope
    with ForecastServices
    with ForecastServiceData {

    override def loadJsonUrl(latitude: Double, longitude: Double)(implicit context: ContextWrapper): String = "http://fake_url/"

    override def loadHeaderTuple(implicit context: ContextWrapper): (String, String) = ("key_name", "key_value")

  }

}

trait ForecastServiceData {

  val validJson: ApiModel = ApiModel(
    id = 5809844,
    dt = 1425060066,
    base = "cmc stations",
    cod = 200,
    name = "Seattle",
    coord = ApiCoord(-122.38, 47.66),
    sys = ApiSys(
      message = Some(0.0168),
      country = Some("US"),
      sunrise = Some(1425048757),
      sunset = Some(1425088297)),
    main = ApiMain(
      temp = Some(6.636),
      temp_min = Some(6.636),
      temp_max = Some(6.636),
      pressure = Some(1009.8),
      sea_level = Some(1021.71),
      grnd_level = Some(1009.8),
      humidity = Some(100)),
    weather = Seq(ApiWeather(id = 501, main = "Rain", description = "moderate rain", icon = "10d")),
    wind = ApiWind(speed = Some(2.17), deg = Some(36.0011)),
    rain = Some(Map("3h" -> 3.5)),
    clouds = ApiClouds(all = Some(92)))

}

class ForecastServicesSpec extends ForecastServiceSpecification {

  "ForecastServices component" should {

    "return forecast with right JSON" in
      new ForecastServiceScope {

        override def loadJson[T](url: String, headers: Seq[(String, String)])(implicit parser: Parser[T], client: OkHttpClient = new OkHttpClient()): Future[T] =
          Future.successful[T](validJson.asInstanceOf[T])

        val forecast = Forecast(
          Location(5809844, "Seattle", 47.66, -122.38),
          Some(Weather(501, "Rain", "moderate rain", "10d", Some(6.636))))

        Await.result(loadForecast(ForecastRequest(0, 0)), Duration.Inf) shouldEqual ForecastResponse(Some(forecast))
      }

    "got the exception thrown by the call to loadJson" in
      new ForecastServiceScope {

        override def loadJson[T](url: String, headers: Seq[(String, String)])(implicit parser: Parser[T], client: OkHttpClient = new OkHttpClient()): Future[T] =
          Future.failed[T](new RuntimeException())

        Await.result(loadForecast(ForecastRequest(0, 0)), Duration.Inf) must throwA[RuntimeException]
      }

  }

}
