package com.fortysevendeg.scala.android.ui.commons

import com.fortysevendeg.scala.android.ui.main.{UserInfo, ProjectActivityInfo}
import play.api.libs.json.Json

trait JsonReads {
  implicit val userInfoReads = Json.reads[UserInfo]
  implicit val projectActivityInfoReads = Json.reads[ProjectActivityInfo]
}
