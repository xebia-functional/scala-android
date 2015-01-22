package com.fortysevendeg.scala.android.ui.commons

import com.fortysevendeg.scala.android.ui.main.ProjectActivityInfo
import play.api.libs.json.Json

trait JsonReads {
  implicit val projectActivityInfoReads = Json.reads[ProjectActivityInfo]
}
