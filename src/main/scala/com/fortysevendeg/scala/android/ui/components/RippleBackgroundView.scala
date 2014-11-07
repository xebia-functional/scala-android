package com.fortysevendeg.scala.android.ui.components

import android.animation.{Animator, AnimatorListenerAdapter}
import android.content.Context
import android.util.AttributeSet
import android.view.View._
import android.view.{ViewAnimationUtils, View, ViewGroup}
import android.widget.FrameLayout
import com.fortysevendeg.scala.android.macroid.SnailsUtils
import macroid.{Snail, ActivityContext}
import macroid.FullDsl._
import com.fortysevendeg.scala.android.macroid.ViewTweaks._

import scala.concurrent.Promise
import scala.util.Success

class RippleBackgroundView(context: Context, attr: AttributeSet, defStyleAttr: Int)(implicit acontext: ActivityContext)
    extends FrameLayout(context, attr, defStyleAttr) {

  def this(context: Context)(implicit acontext: ActivityContext) = this(context, null, 0)

  def this(context: Context, attr: AttributeSet)(implicit acontext: ActivityContext) = this(context, attr, 0)

  val rippleView = {
    var rippleView = slot[View]
    val ui = getUi(
      w[View] <~ wire(rippleView) <~ vInvisible
    )
    addView(ui, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    rippleView
  }

}

object RippleBackgroundSnails {
  def ripple(rippleData : RippleSnailData) = Snail[RippleBackgroundView] {
    view ⇒
      val animPromise = Promise[Unit]()
      view.rippleView.get.setVisibility(View.VISIBLE)
      view.rippleView.get.setBackgroundColor(rippleData.resColor)
      val anim = ViewAnimationUtils.createCircularReveal(view.rippleView.get, rippleData.x, rippleData.y, 0, rippleData.radius)
      anim.addListener(new AnimatorListenerAdapter {
        override def onAnimationCancel(animation: Animator) {
          super.onAnimationCancel(animation)
          rippleData.listener.map(_.onAnimationCancel(animation))
        }
        override def onAnimationRepeat(animation: Animator) {
          super.onAnimationRepeat(animation)
          rippleData.listener.map(_.onAnimationRepeat(animation))
        }
        override def onAnimationStart(animation: Animator) {
          super.onAnimationStart(animation)
          rippleData.listener.map(_.onAnimationStart(animation))
        }
        override def onAnimationPause(animation: Animator) {
          super.onAnimationPause(animation)
          rippleData.listener.map(_.onAnimationPause(animation))
        }
        override def onAnimationResume(animation: Animator) {
          super.onAnimationResume(animation)
          rippleData.listener.map(_.onAnimationResume(animation))
        }
        override def onAnimationEnd(animation: Animator) {
          super.onAnimationEnd(animation)
          view.rippleView.get.setVisibility(INVISIBLE)
          view.setBackgroundColor(rippleData.resColor)
          rippleData.listener.map(_.onAnimationEnd(animation))
          animPromise.complete(Success(()))
        }
      })
      anim.setDuration(rippleData.duration)
      anim.start
      animPromise.future
  }
}


case class RippleSnailData(
    width: Int = 0,
    height: Int = 0,
    x: Int = 0,
    y: Int = 0,
    radius: Int = 0,
    place: Int = RippleSnailData.MIDDLE,
    resColor: Int = 0,
    duration: Int = 300,
    listener: Option[AnimatorListenerAdapter] = None)

object RippleSnailData {

  val TOP = 0
  val TOP_LEFT = 1
  val TOP_RIGHT = 2
  val MIDDLE = 3
  val MIDDLE_LEFT = 4
  val MIDDLE_RIGHT = 5
  val BOTTOM = 6
  val BOTTOM_LEFT = 7
  val BOTTOM_RIGHT = 8

  def fromPlace(view : View,
      place: Int = RippleSnailData.MIDDLE): RippleSnailData = {

    val width = view.getWidth

    val height = view.getHeight

    val x = {
      if ((place == RippleSnailData.MIDDLE) || (place == RippleSnailData.TOP) || (place == RippleSnailData.BOTTOM)) {
        width / 2
      }
      else if ((place == RippleSnailData.TOP_RIGHT) || (place == RippleSnailData.MIDDLE_RIGHT) || (place == RippleSnailData.BOTTOM_RIGHT)) {
        width
      } else 0
    }

    val y = {
      if ((place == RippleSnailData.MIDDLE) || (place == RippleSnailData.MIDDLE_LEFT) || (place == RippleSnailData.MIDDLE_RIGHT)) {
        height / 2
      }
      else if ((place == RippleSnailData.BOTTOM) || (place == RippleSnailData.BOTTOM_LEFT) || (place == RippleSnailData.BOTTOM_RIGHT)) {
        height
      } else 0
    }

    val radius = SnailsUtils.calculateRadius(x, y, width, height)

    RippleSnailData(
      height = height,
      place = place,
      y = y,
      x = x,
      radius = radius)

  }

}
