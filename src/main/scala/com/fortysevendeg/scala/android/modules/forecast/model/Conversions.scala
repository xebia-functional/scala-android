package com.fortysevendeg.scala.android.ui.apirequest.service.model

trait Conversions {
  
  def toForecast(apiModel: ApiModel) =
    Forecast(
      Location(apiModel.id, apiModel.name, apiModel.coord.lat, apiModel.coord.lon),
      apiModel.weather.headOption map (toWeather(_, apiModel.main.temp)))
  
  def toWeather(weather: ApiWeather, temperature: Double) =
    Weather(weather.id, weather.main, weather.description, weather.icon, temperature)

}
