package com.fortysevendeg.scala.android.macroid

import macroid.LogTag

trait Tag {
  implicit val logTag = LogTag("ApiDemos")
}
object Tag extends Tag
