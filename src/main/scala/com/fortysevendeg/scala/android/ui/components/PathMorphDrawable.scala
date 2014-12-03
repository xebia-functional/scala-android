package com.fortysevendeg.scala.android.ui.components

import android.animation.ValueAnimator.AnimatorUpdateListener
import android.animation.{Animator, AnimatorListenerAdapter, ValueAnimator}
import android.graphics.Paint.Style
import android.graphics._
import android.graphics.drawable.{Animatable, Drawable}
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import macroid.{AppContext, Tweak}

class PathMorphDrawable(val defaultIcon: Int = TypeIcons.NOICON, val defaultStroke: Int = 3, val defaultColor: Int = Color.BLACK)(implicit appContext: AppContext)
    extends Drawable
    with Animatable
    with PathMorphDrawableTypes {

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

  lazy val upIcon = List(
    new Segment().fromRatios(0.49f, 0.3f, 0.7f, 0.5f),
    new Segment().fromRatios(0.5f, 0.33f, 0.5f, 0.7f),
    new Segment().fromRatios(0.51f, 0.3f, 0.3f, 0.5f)
  )

  lazy val downIcon = List(
    new Segment().fromRatios(0.51f, 0.7f, 0.3f, 0.5f),
    new Segment().fromRatios(0.5f, 0.67f, 0.5f, 0.3f),
    new Segment().fromRatios(0.49f, 0.7f, 0.7f, 0.5f)
  )

  lazy val nextIcon = List(
    new Segment().fromRatios(0.7f, 0.49f, 0.5f, 0.7f),
    new Segment().fromRatios(0.67f, 0.5f, 0.3f, 0.5f),
    new Segment().fromRatios(0.7f, 0.51f, 0.5f, 0.3f)
  )

  lazy val checkIcon = List(
    new Segment().fromRatios(0.2f, 0.6f, 0.4f, 0.8f),
    new Segment().fromRatios(0.4f, 0.8f, 0.8f, 0.2f)
  )

  lazy val addIcon = List(
    new Segment().fromRatios(0.5f, 0.2f, 0.5f, 0.8f),
    new Segment().fromRatios(0.2f, 0.5f, 0.8f, 0.5f)
  )

  lazy val closeIcon = List(
    new Segment().fromRatios(0.712f, 0.288f, 0.288f, 0.712f),
    new Segment().fromRatios(0.288f, 0.288f, 0.712f, 0.712f)
  )

  val noIcon = List.empty

  val iconPaint: Paint = {
    val paint = new Paint
    paint.setAntiAlias(true)
    paint.setStyle(Style.STROKE)
    paint.setStrokeWidth(defaultStroke)
    paint.setColor(defaultColor)
    paint
  }

  var running = false

  var currentIcon: Option[Icon] = None

  var toIcon: Option[Icon] = None

  var transformIcon: Option[Icon] = None

  override def onBoundsChange(bounds: Rect): Unit = {
    super.onBoundsChange(bounds)
    size = Some(new Dim(bounds.width(), bounds.height()))
    setTypeIcon(defaultIcon)
  }

  override def draw(canvas: Canvas): Unit = {
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

  def setColor(color: Int): Unit = {
    iconPaint.setColor(color)
    invalidateSelf()
  }

  def setColorResource(color: Int): Unit = {
    iconPaint.setColor(appContext.get.getResources.getColor(color))
    invalidateSelf()
  }

  def setStroke(stroke: Float) = {
    iconPaint.setStrokeWidth(stroke)
    invalidateSelf()
  }

  def setTransformIcon(icon: Icon) = {
    transformIcon = Some(icon)
    invalidateSelf()
  }

  def setIcon(icon: Icon) = {
    currentIcon = Some(icon)
    invalidateSelf()
  }

  def setToIcon(icon: Icon) = {
    toIcon = Some(icon)
  }

  def setTypeIcon(icon: Int) = {
    icon match {
      case TypeIcons.ADD => setIcon(addIcon)
      case TypeIcons.BACK => setIcon(backIcon)
      case TypeIcons.BURGER => setIcon(burgerIcon)
      case TypeIcons.CHECK => setIcon(checkIcon)
      case TypeIcons.CLOSE => setIcon(closeIcon)
      case TypeIcons.DOWN => setIcon(downIcon)
      case TypeIcons.NEXT => setIcon(nextIcon)
      case TypeIcons.NOICON => setIcon(noIcon)
      case TypeIcons.UP => setIcon(upIcon)
    }
  }

  def setToTypeIcon(icon: Int) = {
    icon match {
      case TypeIcons.ADD => setToIcon(addIcon)
      case TypeIcons.BACK => setToIcon(backIcon)
      case TypeIcons.BURGER => setToIcon(burgerIcon)
      case TypeIcons.CHECK => setToIcon(checkIcon)
      case TypeIcons.CLOSE => setToIcon(closeIcon)
      case TypeIcons.DOWN => setToIcon(downIcon)
      case TypeIcons.NEXT => setToIcon(nextIcon)
      case TypeIcons.NOICON => setToIcon(noIcon)
      case TypeIcons.UP => setToIcon(upIcon)
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
  val NOICON = 0
  val BURGER = 1
  val BACK = 2
  val CHECK = 3
  val ADD = 4
  val UP = 5
  val DOWN = 6
  val NEXT = 7
  val CLOSE = 8
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
    Segment(Point(x1, y1), Point(x2, y2))
  }
}

object PathMorphDrawableTweaks {
  type W = ImageView

  def pmdAnimIcon(icon: Int) = Tweak[W] {
    view =>
      view.getDrawable.asInstanceOf[PathMorphDrawable].setToTypeIcon(icon)
      view.getDrawable.asInstanceOf[PathMorphDrawable].start
  }
  def pmdChangeIcon(icon: Int) = Tweak[W](_.getDrawable.asInstanceOf[PathMorphDrawable].setTypeIcon(icon))
  def pmdColor(color: Int) = Tweak[W](_.getDrawable.asInstanceOf[PathMorphDrawable].setColor(color))
  def pmdColorResource(color: Int) = Tweak[W](_.getDrawable.asInstanceOf[PathMorphDrawable].setColorResource(color))
  def pmdStroke(stroke: Float) = Tweak[W](_.getDrawable.asInstanceOf[PathMorphDrawable].setStroke(stroke))
}
