package com.fortysevendeg.scala.android.ui.components

import android.animation.{Animator, AnimatorListenerAdapter}
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View._
import android.view.{View, ViewAnimationUtils, ViewGroup}
import android.widget.FrameLayout
import com.fortysevendeg.macroid.extras.SnailsUtils
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.FullDsl._
import macroid.{ActivityContextWrapper, Snail}

import scala.concurrent.Promise

class RippleBackgroundView(context: Context, attr: AttributeSet, defStyleAttr: Int)(implicit acontext: ActivityContextWrapper)
    extends FrameLayout(context, attr, defStyleAttr) {

  def this(context: Context)(implicit acontext: ActivityContextWrapper) = this(context, null, 0)

  def this(context: Context, attr: AttributeSet)(implicit acontext: ActivityContextWrapper) = this(context, attr, 0)

  val rippleView: View = {
    val rippleView = getUi(
      w[View] <~ vInvisible
    )
    addView(rippleView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    rippleView
  }

}

object RippleBackgroundSnails {
  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  def ripple(rippleData : RippleSnailData) = Snail[RippleBackgroundView] {
    view ⇒
      val animPromise = Promise[Unit]()
      view.rippleView.setVisibility(View.VISIBLE)
      view.rippleView.setBackgroundColor(rippleData.resColor)
      val anim = ViewAnimationUtils.createCircularReveal(view.rippleView, rippleData.x, rippleData.y, 0, rippleData.radius)
      anim.addListener(new AnimatorListenerAdapter {
        override def onAnimationEnd(animation: Animator) {
          super.onAnimationEnd(animation)
          view.rippleView.setVisibility(INVISIBLE)
          view.setBackgroundColor(rippleData.resColor)
          rippleData.onAnimationEnd foreach (listener => listener())
          animPromise.success()
        }
      })
      anim.setDuration(rippleData.duration)
      anim.start()
      animPromise.future
  }
}


case class RippleSnailData(
    width: Int = 0,
    height: Int = 0,
    x: Int = 0,
    y: Int = 0,
    radius: Int = 0,
    resColor: Int = 0,
    duration: Int = 300,
    onAnimationEnd: Option[() ⇒ Unit] = None)

object RippleSnailData {

  def toCenterView(view : View): RippleSnailData = {

    val width = view.getWidth

    val height = view.getHeight

    val x = width / 2

    val y = height / 2

    val radius = SnailsUtils.calculateRadius(x, y, width, height)

    RippleSnailData(
      height = height,
      y = y,
      x = x,
      radius = radius)

  }

}
