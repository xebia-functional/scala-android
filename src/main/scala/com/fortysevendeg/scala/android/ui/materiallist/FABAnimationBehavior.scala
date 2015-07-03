package com.fortysevendeg.scala.android.ui.materiallist

import android.animation.{Animator, AnimatorListenerAdapter}
import android.content.Context
import android.support.design.widget.{CoordinatorLayout, FloatingActionButton}
import android.support.v4.view.ViewCompat
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.View
import macroid.Snail
import macroid.FullDsl._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Promise

class FABAnimationBehavior
    extends FloatingActionButton.Behavior {

  def this(context: Context, attrs: AttributeSet) = this()

  var isAnimatingOut = false

  val interpolator = new FastOutSlowInInterpolator()

  val duration = 200L

  override def onStartNestedScroll(
      coordinatorLayout: CoordinatorLayout,
      child: FloatingActionButton,
      directTargetChild: View,
      target: View,
      nestedScrollAxes: Int): Boolean =
    nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
        super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes)

  override def onNestedScroll(
      coordinatorLayout: CoordinatorLayout,
      child: FloatingActionButton,
      target: View,
      dxConsumed: Int,
      dyConsumed: Int,
      dxUnconsumed: Int,
      dyUnconsumed: Int): Unit = {
    super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)

    (dyConsumed, child) match {
      case (d, c) if d > 0 && !isAnimatingOut && c.getVisibility == View.VISIBLE =>
        runUi(Option(child) <~~ animateOut)
      case (d, c) if d < 0 && c.getVisibility != View.VISIBLE =>
        runUi(Option(child) <~~ animateIn)
      case _ =>
    }
  }

  val animateIn = Snail[FloatingActionButton] {
    view ⇒
      view.setVisibility(View.VISIBLE)
      val animPromise = Promise[Unit]()
      view.animate
        .translationY(0)
        .setInterpolator(interpolator)
        .setDuration(duration)
        .setListener(new AnimatorListenerAdapter {
          override def onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            animPromise.success()
          }
        }).start()
      animPromise.future
  }

  val animateOut = Snail[FloatingActionButton] {
    view ⇒
      val animPromise = Promise[Unit]()
      val y = view.getHeight + (view.getPaddingBottom * 2)
      view.animate
        .translationY(y)
        .setInterpolator(interpolator)
        .setDuration(duration)
        .setListener(new AnimatorListenerAdapter {
          override def onAnimationStart(animation: Animator): Unit = {
            super.onAnimationStart(animation)
            isAnimatingOut = true
          }
          override def onAnimationCancel(animation: Animator): Unit = {
            super.onAnimationCancel(animation)
            isAnimatingOut = false
          }
          override def onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            isAnimatingOut = false
            view.setVisibility(View.GONE)
            animPromise.success()
          }
        }).start()
      animPromise.future
  }
}