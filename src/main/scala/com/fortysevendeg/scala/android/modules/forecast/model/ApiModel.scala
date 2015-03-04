package com.fortysevendeg.scala.android.modules.forecast.model

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
  message: Option[Double],
  country: Option[String],
  sunrise: Option[Long],
  sunset: Option[Long])

case class ApiMain(
  temp: Option[Double],
  temp_min: Option[Double],
  temp_max: Option[Double],
  pressure: Option[Double],
  sea_level: Option[Double],
  grnd_level: Option[Double],
  humidity: Option[Int])

case class ApiWeather(
  id: Int,
  main: String,
  description: String,
  icon: String)

case class ApiWind(
  speed: Option[Double],
  deg: Option[Double])

case class ApiClouds(
  all: Option[Int])