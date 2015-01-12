package com.fortysevendeg.scala.android.ui.commons

import com.fortysevendeg.scala.android.ui.main.ProjectActivityInfo
import spray.json._

object JsonProtocol extends DefaultJsonProtocol {

  implicit val projectActivityInfoFormat = jsonFormat[String, String, String, Int, Int, ProjectActivityInfo](ProjectActivityInfo.apply, "name", "description", "className", "minApi", "targetApi")
}
