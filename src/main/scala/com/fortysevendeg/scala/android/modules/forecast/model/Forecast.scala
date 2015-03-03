package com.fortysevendeg.scala.android.ui.apirequest.service.model

case class Forecast(location: Location, weather: Option[Weather])

case class Location(id: Double, name: String, latitude: Double, longitude: Double)

case class Weather(id: Int, name: String, description: String, temperature: Double)