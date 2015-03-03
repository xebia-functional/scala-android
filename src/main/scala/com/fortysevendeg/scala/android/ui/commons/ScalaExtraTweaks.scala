/*
 * Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may
 *  not use this file except in compliance with the License. You may obtain
 *  a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.fortysevendeg.scala.android.ui.commons

import android.widget.ImageView
import com.fortysevendeg.macroid.extras.DeviceVersion._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.ui.components.CircularTransformation
import com.squareup.picasso.Picasso
import macroid.{ActivityContext, AppContext, Tweak}

import scala.language.postfixOps

object AsyncImageTweaks {
  type W = ImageView

  def roundedImage(url: String,
        placeHolder: Int,
        size: Int)(implicit appContext: AppContext, activityContext: ActivityContext) = CurrentVersion match {
    case sdk if sdk >= Lollipop =>
      srcImage(url, placeHolder) + vCircleOutlineProvider
    case _ =>
      roundedImageTweak(url, placeHolder, size)
  }

  private def roundedImageTweak(
      url: String,
      placeHolder: Int,
      size: Int
      )(implicit appContext: AppContext, activityContext: ActivityContext): Tweak[W] = Tweak[W](
    imageView => {
      Picasso.`with`(activityContext.get)
          .load(url)
          .transform(new CircularTransformation(size))
          .placeholder(placeHolder)
          .into(imageView)
    }
  )

  def srcImage(
      url: String,
      placeHolder: Int
      )(implicit appContext: AppContext, activityContext: ActivityContext): Tweak[W] = Tweak[W](
    imageView => {
      Picasso.`with`(activityContext.get)
          .load(url)
          .placeholder(placeHolder)
          .into(imageView)
    }
  )

  def srcImage(url: String)(implicit appContext: AppContext, activityContext: ActivityContext): Tweak[W] = Tweak[W](
    imageView => {
      Picasso.`with`(activityContext.get)
          .load(url)
          .into(imageView)
    }
  )
}

