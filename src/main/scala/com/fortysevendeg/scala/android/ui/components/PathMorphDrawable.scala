package com.fortysevendeg.scala.android.ui.components

import android.graphics.Paint.Style
import android.graphics._
import android.graphics.drawable.{Animatable, Drawable}
import android.widget.ImageView
import macroid.{Tweak, AppContext}
import macroid.FullDsl._

class PathMorphDrawable(implicit appContext: AppContext) extends Drawable with Animatable {

  type Icon = List[Segment]

  implicit var size: Option[Dim] = None

  lazy val burgerIcon = List(
    new Segment(0.2f, 0.3f, 0.8f, 0.3f),
    new Segment(0.2f, 0.5f, 0.8f, 0.5f),
    new Segment(0.2f, 0.7f, 0.8f, 0.7f)
  )

  lazy val backIcon = List(
    new Segment(0.3f, 0.51f, 0.5f, 0.3f),
    new Segment(0.33f, 0.5f, 0.7f, 0.5f),
    new Segment(0.3f, 0.49f, 0.5f, 0.7f)
  )

  lazy val checkIcon = List(
    new Segment(0.2f, 0.6f, 0.4f, 0.8f),
    new Segment(0.4f, 0.8f, 0.8f, 0.2f)
  )

  lazy val addIcon = List(
    new Segment(0.5f, 0.2f, 0.5f, 0.8f),
    new Segment(0.2f, 0.5f, 0.8f, 0.5f)
  )

  val iconPaint: Paint = {
    val paint = new Paint
    paint.setAntiAlias(true)
    paint.setStyle(Style.STROKE)
    paint.setStrokeWidth(3 dp)
    paint.setColor(Color.BLACK)
    paint
  }

  var currentIcon: Option[Icon] = None

  override def onBoundsChange(bounds: Rect): Unit = {
    super.onBoundsChange(bounds)
    size = Some(new Dim(bounds.width(), bounds.height()))
  }

  override def draw(canvas: Canvas): Unit = {
    canvas.drawColor(Color.GRAY)
    currentIcon.map(drawIcon(canvas, _))
  }

  override def setColorFilter(cf: ColorFilter): Unit = iconPaint.setColorFilter(cf)

  override def setAlpha(alpha: Int): Unit = iconPaint.setAlpha(alpha)

  override def getOpacity: Int = PixelFormat.TRANSPARENT

  override def stop(): Unit = ???

  override def isRunning: Boolean = ???

  override def start(): Unit = ???

  def setStroke(stroke: Float) = {
    iconPaint.setStrokeWidth(stroke)
  }

  def setCurrentIcon(icon: Icon) = {
    currentIcon = Some(icon)
  }

  def setIcon(icon: Int) = {
    if (icon == TypeIcons.BURGER) {
      setCurrentIcon(burgerIcon)
    } else if (icon == TypeIcons.BACK) {
      setCurrentIcon(backIcon)
    } else if (icon == TypeIcons.CHECK) {
      setCurrentIcon(checkIcon)
    } else if (icon == TypeIcons.ADD) {
      setCurrentIcon(addIcon)
    }
    invalidateSelf()
  }

  private def drawIcon(canvas: Canvas, icon: Icon): Unit = {
    icon.map(drawSegment(canvas, _))
  }

  private def drawSegment(canvas: Canvas, segment: Segment): Unit = {
    canvas.drawLine(segment.x1, segment.y1, segment.x2, segment.y2, iconPaint)
  }

}

object TypeIcons {
  val BURGER = 0
  val BACK = 1
  val CHECK = 2
  val ADD = 3
}

case class Dim(wight: Int, height: Int)

case class Segment(
    ratioX1: Float,
    ratioY1: Float,
    ratioX2: Float,
    ratioY2: Float)
    (implicit val dim: Option[Dim]) {

  val (x1: Float, y1: Float, x2: Float, y2: Float, size: Float) = dim.map {
    value =>
      val x1 = (ratioX1 * value.wight)
      val y1 = (ratioY1 * value.height)
      val x2 = (ratioX2 * value.wight)
      val y2 = (ratioY2 * value.height)

      val catheti1 = x2 - x1
      val catheti2 = y2 - y1

      val size = Math.sqrt((catheti1 * catheti1) + (catheti2 * catheti2)).asInstanceOf[Float]
      (x1, y1, x2, y2, size)
  }.getOrElse(0f, 0f, 0f, 0f, 0f)

}

object PathMorphDrawableTweaks {
  type W = ImageView

  def changeIcon(icon: Int) = Tweak[W](_.getDrawable.asInstanceOf[PathMorphDrawable].setIcon(icon))

}
