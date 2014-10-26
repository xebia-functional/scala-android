package com.fortysevendeg.scala.android.macroid

import android.animation.{Animator, AnimatorListenerAdapter}
import android.view.View._
import android.view.{ViewAnimationUtils, View}
import com.fortysevendeg.scala.android.ui.components.RippleBackgroundView
import macroid.Snail

import android.view.animation.{ AlphaAnimation, Animation }
import android.view.View
import scala.concurrent.{ Future, Promise, ExecutionContext }
import android.view.animation.Animation.AnimationListener
import scala.util.Success
import android.widget.ProgressBar
import java.util.concurrent.{ TimeUnit, Executors }
import scala.util.control.NonFatal

object RevealSnails {

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

    val radius = {
      val catheti1: Int = if (x < width / 2) width - x else x
      val catheti2: Int = if (y < height / 2) height - y else y
      Math.sqrt((catheti1 * catheti1) + (catheti2 * catheti2)).asInstanceOf[Int]
    }

    RippleSnailData(
      height = height,
      place = place,
      y = y,
      x = x,
      radius = radius)

  }

}
