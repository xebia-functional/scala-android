/*
 * Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fortysevendeg.scala.android.ui.components

import android.graphics.{Bitmap, Canvas, Paint, PorterDuff, PorterDuffXfermode, Rect}
import com.squareup.picasso.Transformation

class CircularTransformation(size: Int) extends Transformation {

  val radius = Math.ceil(size / 2).toInt

  def transform(source: Bitmap): Bitmap = {
    val output: Bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    val canvas: Canvas = new Canvas(output)
    val color: Int = 0xff424242
    val paint: Paint = new Paint
    val rect: Rect = new Rect(0, 0, source.getWidth, source.getHeight)
    val target: Rect = new Rect(0, 0, size, size)
    paint.setAntiAlias(true)
    canvas.drawARGB(0, 0, 0, 0)
    paint.setColor(color)
    canvas.drawCircle(radius, radius, radius, paint)
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
    canvas.drawBitmap(source, rect, target, paint)
    source.recycle()
    output
  }

  def key: String = {
    s"radius-$size"
  }
}