package com.fortysevendeg.scala.android.ui.components

/**
 * https://github.com/keithellis/MaterialWidget/blob/master/library/src/main/java/com/material/widget/CircleButton.java
 */

import android.content.Context
import android.graphics._
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View.MeasureSpec
import android.view.{MotionEvent, View}
import com.fortysevendeg.scala.android.R
import macroid.Tweak

class CircleButton(context: Context, attrs: AttributeSet, defStyleAttr: Int)
    extends View(context, attrs, defStyleAttr) {

  val SHADOW_RADIUS = 10.0f
  val SHADOW_OFFSET_X = 0.0f
  val SHADOW_OFFSET_Y = 3.0f

  def this(context: Context) = this(context, null, 0)

  def this(context: Context, attr: AttributeSet) = this(context, attr, 0)

  val buttonWidth = getResources().getDimensionPixelSize(R.dimen.circle_button_width)

  val buttonHeight = getResources().getDimensionPixelSize(R.dimen.circle_button_height)

  val iconPaint = new Paint(Paint.ANTI_ALIAS_FLAG)

  var moveOutside: Boolean = false

  var icon: Option[Bitmap] = None

  val (circlePaint, color) = {
    val paint = new Paint(Paint.ANTI_ALIAS_FLAG)
    val color = getResources().getColor(R.color.circle_button_color)
    val shadowColor = getResources().getColor(R.color.circle_button_shadow_color)
    paint.setColor(color)
    paint.setStyle(Paint.Style.FILL)
    paint.setShadowLayer(SHADOW_RADIUS, SHADOW_OFFSET_X, SHADOW_OFFSET_Y, shadowColor)
    (paint, color)
  }

  setWillNotDraw(false)
  setLayerType(View.LAYER_TYPE_SOFTWARE, null)


  override def onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int): Unit = {

    val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
    val widthSpecSize = MeasureSpec.getSize(heightMeasureSpec)

    val widthSize = {
      var size: Int = buttonWidth
      if (widthSpecMode == MeasureSpec.EXACTLY) {
        if (widthSpecSize < buttonWidth) {
          size = buttonWidth
        } else {
          size = widthSpecSize
        }
      }
      size
    }

    val newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);

    val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
    val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)

    val heightSize = {
      var size: Int = buttonHeight
      if (heightSpecMode == MeasureSpec.EXACTLY) {
        if (heightSpecSize < buttonHeight) {
          size = buttonHeight
        } else {
          size = heightSpecSize
        }
      }
      size
    }

    val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
    super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec);
  }

  override def onTouchEvent(event: MotionEvent): Boolean = {
    event.getAction match {
      case action if (action == MotionEvent.ACTION_DOWN) =>
        moveOutside = false
        circlePaint.setColor(darkenColor(color))
        invalidate()
      case action if (action == MotionEvent.ACTION_MOVE) =>
        val rect = new Rect()
        getLocalVisibleRect(rect)
        if (!rect.contains(event.getX().toInt, event.getY().toInt)) {
          moveOutside = true
          circlePaint.setColor(color)
          invalidate()
        }
      case action if (action == MotionEvent.ACTION_UP) =>
        circlePaint.setColor(color)
        invalidate()
        if (!moveOutside) {
          performClick()
        }
      case action if (action == MotionEvent.ACTION_CANCEL) =>
        circlePaint.setColor(color)
        invalidate()
    }
    true
  }

  override def onDraw(canvas: Canvas): Unit = {
    super.onDraw(canvas)
    canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2.6f, circlePaint)
    icon.map {
      bitmap =>
        val x = (getWidth() - bitmap.getWidth()) / 2
        val y = (getHeight() - bitmap.getHeight()) / 2
        canvas.drawBitmap(bitmap, x, y, iconPaint)
    }
  }

  def darkenColor(color: Int): Int = {
    var hsv = Seq(0f, 0f, 0f).toArray
    Color.colorToHSV(color, hsv)
    hsv(2) *= 0.8f
    Color.HSVToColor(hsv)
  }

  def drawable(res: Int) = {
    icon = Some(getResources.getDrawable(res).asInstanceOf[BitmapDrawable].getBitmap)
  }

  def color(res: Int) = {
    val color = getResources().getColor(res)
    circlePaint.setColor(color)
  }

}

object CircleButtonTweaks {
  type W = CircleButton
  def cbDrawable(res: Int) = Tweak[W](_.drawable(res))
  def cbColor(res: Int) = Tweak[W](_.color(res))
}
