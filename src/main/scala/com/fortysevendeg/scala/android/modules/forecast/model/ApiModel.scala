package com.fortysevendeg.scala.android.ui.apirequest.service.model

case class ApiModel(
  id: Long, 
  dt: Long, 
  base: String, 
  cod: Int, 
  name: String, 
  coord: ApiCoord, 
  sys: ApiSys, 
  main: ApiMain,
  weather: Seq[ApiWeather],
  wind: ApiWind,
  rain: Option[Map[String, Double]],
  clouds: ApiClouds)

case class ApiCoord(
  lon: Double, 
  lat: Double)

case class ApiSys(
  message: Double, 
  country: String, 
  sunrise: Long, sunset: Long)

case class ApiMain(
  temp: Double, 
  temp_min: Double, 
  temp_max: Double, 
  pressure: Double, 
  sea_level: Double, 
  grnd_level: Double,
  humidity: Int)

case class ApiWeather(
  id: Int,
  main: String,
  description: String,
  icon: String)

case class ApiWind(
  speed: Double,
  deg: Double)

case class ApiClouds(
  all: Int)