package com.fortysevendeg.scala.android.ui.ripplebackground

import android.animation.{Animator, AnimatorListenerAdapter}
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.MenuItem
import com.fortysevendeg.macroid.extras.MoveSnails._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.components.CircleView._
import com.fortysevendeg.scala.android.ui.components.RippleBackgroundSnails._
import com.fortysevendeg.scala.android.ui.components.{CircleView, RippleSnailData}
import macroid.Contexts
import macroid.FullDsl._

import scala.concurrent.ExecutionContext.Implicits.global

class RippleBackgroundActivity
  extends ActionBarActivity
  with Contexts[ActionBarActivity]
  with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    val rippleSnailData = RippleSnailData.fromPlace(rippleBackground.get)

    val color1 = resGetColor(R.color.ripple_bg_color1)
    val color2 = resGetColor(R.color.ripple_bg_color2)
    val color3 = resGetColor(R.color.ripple_bg_color3)

    runUi(
      (rippleBackground <~ vBackgroundColor(color1)) ~
        (circle1 <~ cvColor(color1) <~ On.click(anim(circle1, color1))) ~
        (circle2 <~ cvColor(color2) <~ On.click(anim(circle2, color2))) ~
        (circle3 <~ cvColor(color3) <~ On.click(anim(circle3, color3)))
    )

    toolBar map setSupportActionBar

    getSupportActionBar.setDisplayHomeAsUpEnabled(true)

  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case android.R.id.home => {
        finish()
        false
      }
    }
    super.onOptionsItemSelected(item)
  }

  def anim(circleView: Option[CircleView], color: Int) = {

    val rippleData = RippleSnailData.fromPlace(rippleBackground.get).
      copy(
        resColor = color,
        listener = Some(new AnimatorListenerAdapter {
          override def onAnimationEnd(animation: Animator): Unit = {
            runUi(circleView <~ vTransformation(0, 0))
          }
        })
      )
    (circleView <~~ move(rippleBackground)) ~~ (rippleBackground <~~ ripple(rippleData)) ~~ (circleView <~~ fadeIn(1000))
  }

}
