package com.fortysevendeg.scala.android.ui.components

import android.animation.ValueAnimator.AnimatorUpdateListener
import android.animation.{Animator, AnimatorListenerAdapter, ValueAnimator}
import android.graphics.Paint.Style
import android.graphics._
import android.graphics.drawable.{Animatable, Drawable}
import android.view.animation.{DecelerateInterpolator, AccelerateInterpolator}
import android.widget.ImageView
import macroid.{LogTag, AppContext, Tweak}
import macroid.FullDsl._

class PathMorphDrawable(implicit appContext: AppContext) extends Drawable with Animatable with PathMorphDrawableTypes {

  implicit var size: Option[Dim] = None

  lazy val burgerIcon = List(
    new Segment().fromRatios(0.2f, 0.3f, 0.8f, 0.3f),
    new Segment().fromRatios(0.2f, 0.5f, 0.8f, 0.5f),
    new Segment().fromRatios(0.2f, 0.7f, 0.8f, 0.7f)
  )

  lazy val backIcon = List(
    new Segment().fromRatios(0.3f, 0.51f, 0.5f, 0.3f),
    new Segment().fromRatios(0.33f, 0.5f, 0.7f, 0.5f),
    new Segment().fromRatios(0.3f, 0.49f, 0.5f, 0.7f)
  )

  lazy val checkIcon = List(
    new Segment().fromRatios(0.2f, 0.6f, 0.4f, 0.8f),
    new Segment().fromRatios(0.4f, 0.8f, 0.8f, 0.2f)
  )

  lazy val addIcon = List(
    new Segment().fromRatios(0.5f, 0.2f, 0.5f, 0.8f),
    new Segment().fromRatios(0.2f, 0.5f, 0.8f, 0.5f)
  )

  val iconPaint: Paint = {
    val paint = new Paint
    paint.setAntiAlias(true)
    paint.setStyle(Style.STROKE)
    paint.setStrokeWidth(3 dp)
    paint.setColor(Color.BLACK)
    paint
  }

  var running = false

  var currentIcon: Option[Icon] = None

  var toIcon: Option[Icon] = None

  var transformIcon: Option[Icon] = None

  override def onBoundsChange(bounds: Rect): Unit = {
    super.onBoundsChange(bounds)
    size = Some(new Dim(bounds.width(), bounds.height()))
  }

  override def draw(canvas: Canvas): Unit = {
    canvas.drawColor(Color.GRAY)
    if (running) {
      transformIcon.map(drawIcon(canvas, _))
    } else {
      currentIcon.map(drawIcon(canvas, _))
    }
  }

  override def setColorFilter(cf: ColorFilter): Unit = iconPaint.setColorFilter(cf)

  override def setAlpha(alpha: Int): Unit = iconPaint.setAlpha(alpha)

  override def getOpacity: Int = PixelFormat.TRANSPARENT

  override def stop(): Unit = {
    toIcon map (setIcon(_))
    toIcon = None
    running = false
  }

  override def isRunning: Boolean = running

  override def start(): Unit = {
    if (!toIcon.isEmpty) {
      if (!currentIcon.isEmpty) {
        running = true
        moveIcon(currentIcon.get, toIcon.get)
      } else {
        toIcon map (setIcon(_))
        toIcon = None
      }
    }
  }

  def setStroke(stroke: Float) = {
    iconPaint.setStrokeWidth(stroke)
  }

  def setTransformIcon(icon: Icon) = {
    transformIcon = Some(icon)
    invalidateSelf()
  }

  def setIcon(icon: Icon) = {
    currentIcon = Some(icon)
    invalidateSelf()
  }

  def setTypeIcon(icon: Int) = {
    if (icon == TypeIcons.BURGER) {
      setIcon(burgerIcon)
    } else if (icon == TypeIcons.BACK) {
      setIcon(backIcon)
    } else if (icon == TypeIcons.CHECK) {
      setIcon(checkIcon)
    } else if (icon == TypeIcons.ADD) {
      setIcon(addIcon)
    }
  }

  def setToIcon(icon: Icon) = {
    toIcon = Some(icon)
  }

  def setToTypeIcon(icon: Int) = {
    if (icon == TypeIcons.BURGER) {
      setToIcon(burgerIcon)
    } else if (icon == TypeIcons.BACK) {
      setToIcon(backIcon)
    } else if (icon == TypeIcons.CHECK) {
      setToIcon(checkIcon)
    } else if (icon == TypeIcons.ADD) {
      setToIcon(addIcon)
    }
  }

  private def drawIcon(canvas: Canvas, icon: Icon): Unit = {
    icon.map(drawSegment(canvas, _))
  }

  private def drawSegment(canvas: Canvas, segment: Segment): Unit = {
    iconPaint.setAlpha((segment.alpha * 255).toInt)
    canvas.drawLine(segment.point1.x, segment.point1.y, segment.point2.x, segment.point2.y, iconPaint)
  }

  def moveIcon(from: Icon, to: Icon) = {
    val valueAnimator: ValueAnimator = ValueAnimator.ofInt(0, 100)
    valueAnimator.addUpdateListener(new AnimatorUpdateListener {
      override def onAnimationUpdate(animation: ValueAnimator): Unit = {
        val fraction = animation.getAnimatedFraction

        val fromOver = from.drop(to.length)
        val toOver = to.drop(from.length)

        val transform = from.zip(to).map {
          i =>
            transformSegment(i._1, i._2, fraction)
        }

        val segmentFromOver = fromOver.map {
          segment =>
            segment.copy(alpha = 1 - fraction)
        }

        val segmentToOver = toOver.map(
          segment =>
            transformSegment(new Segment(Point(segment.point1.x + 1, segment.point1.y + 1), Point(segment.point1.x, segment.point1.y)), segment, fraction)
        )

        val list = transform ++ segmentFromOver ++ segmentToOver

        setTransformIcon(list)

      }
    })
    valueAnimator.setInterpolator(new DecelerateInterpolator())
    valueAnimator.addListener(new AnimatorListenerAdapter {
      override def onAnimationEnd(animation: Animator): Unit = {
        super.onAnimationEnd(animation)
        stop()
      }
    })
    valueAnimator.start()
  }

  def transformSegment(from: Segment, to: Segment, fraction: Float): Segment = {
    if (from.equals(to)) {
      from
    } else {
      val point1 = calculatePoint(from.point1, to.point1, fraction)
      val point2 = calculatePoint(from.point2, to.point2, fraction)

      Segment(point1, point2)
    }
  }

  def calculatePoint(from: Point, to: Point, fraction: Float): Point = {
    val cathetiX = to.x - from.x
    val cathetiY = to.y - from.y

    val hypotenuse = Math.sqrt((cathetiX * cathetiX) + (cathetiY * cathetiY)).toFloat
    val angle = Math.atan(cathetiY / cathetiX)

    val rFraction = hypotenuse * fraction

    val coordX = rFraction * Math.cos(angle).toFloat
    val coordY = rFraction * Math.sin(angle).toFloat

    if (cathetiX >= 0)
      Point(from.x + coordX, from.y + coordY)
    else
      Point(from.x - coordX, from.y - coordY)
  }

}

trait PathMorphDrawableTypes {
  type Icon = List[Segment]
}

object TypeIcons {
  val BURGER = 0
  val BACK = 1
  val CHECK = 2
  val ADD = 3
}

case class Dim(wight: Int, height: Int)

case class Point(x: Float, y: Float)

case class Segment(
    point1: Point = Point(0, 0),
    point2: Point = Point(0, 0),
    alpha: Float = 1) {

  def fromRatios(ratioX1: Float,
      ratioY1: Float,
      ratioX2: Float,
      ratioY2: Float)(implicit dim: Option[Dim]): Segment = {
    val (x1: Float, y1: Float, x2: Float, y2: Float) = dim.map {
      value =>
        val x1 = ratioX1 * value.wight
        val y1 = ratioY1 * value.height
        val x2 = ratioX2 * value.wight
        val y2 = ratioY2 * value.height
        (x1, y1, x2, y2)
    }.getOrElse(0f, 0f, 0f, 0f, 0f)
    new Segment(Point(x1, y1), Point(x2, y2))
  }

}

object PathMorphDrawableTweaks {
  type W = ImageView

  def changeIcon(icon: Int) = Tweak[W](_.getDrawable.asInstanceOf[PathMorphDrawable].setTypeIcon(icon))

  def animIcon(icon: Int) = Tweak[W] {
    view =>
      view.getDrawable.asInstanceOf[PathMorphDrawable].setToTypeIcon(icon)
      view.getDrawable.asInstanceOf[PathMorphDrawable].start
  }

}
