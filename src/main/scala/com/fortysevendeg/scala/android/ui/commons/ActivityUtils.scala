package com.fortysevendeg.scala.android.ui.commons

import android.view.View
import macroid.ActivityContext

object ActivityUtils {

  def find[R <: View](resource: Int)(implicit activityContext: ActivityContext): Option[R] =
    Option(activityContext.get.findViewById(resource).asInstanceOf[R])
}