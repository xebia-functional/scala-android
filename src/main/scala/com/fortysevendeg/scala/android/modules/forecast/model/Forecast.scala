package com.fortysevendeg.scala.android.modules.forecast.model

case class Forecast(location: Location, weather: Option[Weather])

case class Location(id: Long, name: String, latitude: Double, longitude: Double)

case class Weather(id: Int, name: String, description: String, icon: String, temperature: Double)