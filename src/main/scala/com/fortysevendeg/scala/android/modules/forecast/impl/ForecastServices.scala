/*
 * Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fortysevendeg.scala.android.modules.forecast.impl

import java.io.InputStream

import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.modules.forecast.model._
import com.fortysevendeg.scala.android.modules.forecast.{ForecastRequest, ForecastResponse}
import com.fortysevendeg.scala.android.modules.utils.NetUtils
import io.taig.communicator.response.Plain
import io.taig.communicator.result.Parser
import macroid.ContextWrapper
import play.api.libs.json.Json

import scala.concurrent.Future

trait ApiReads {

  implicit val apiCloudsReads = Json.reads[ApiClouds]
  implicit val apiWindReads = Json.reads[ApiWind]
  implicit val apiWeatherReads = Json.reads[ApiWeather]
  implicit val apiMainReads = Json.reads[ApiMain]
  implicit val apiSysReads = Json.reads[ApiSys]
  implicit val apiCoordReads = Json.reads[ApiCoord]
  implicit val apiModelReads = Json.reads[ApiModel]

}

object JsonParser 
  extends Parser[ApiModel]
  with ApiReads {
  
  override def parse(response: Plain, stream: InputStream): ApiModel = 
    Json.parse(scala.io.Source.fromInputStream(stream).mkString).as[ApiModel]
 
}

trait ForecastServices
    extends NetUtils
    with Conversions {
  
  def loadJsonUrl(latitude: Double, longitude: Double)(implicit context: ContextWrapper): String =
    resGetString(R.string.openweather_url, latitude.toString, longitude.toString)
  
  def loadHeaderTuple(implicit context: ContextWrapper): (String, String) =
    (resGetString(R.string.openweather_key_name), resGetString(R.string.openweather_key_value))
  
  def loadForecast(request: ForecastRequest)(implicit context: ContextWrapper): Future[ForecastResponse] = {
    import scala.concurrent.ExecutionContext.Implicits.global
    
    implicit val parser = JsonParser
    
    val result = loadJson[ApiModel](loadJsonUrl(request.latitude, request.longitude), Seq(loadHeaderTuple))
    result.transform(
      response => ForecastResponse(Some(toForecast(response))),
      throwable => throwable)
  }
}

object ForecastServices extends ForecastServices
