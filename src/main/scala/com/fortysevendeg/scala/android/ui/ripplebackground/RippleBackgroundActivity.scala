package com.fortysevendeg.scala.android.ui.ripplebackground

import android.animation.{Animator, AnimatorListenerAdapter}
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.MenuItem
import com.fortysevendeg.macroid.extras.MoveSnails._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.ui.components.RippleBackgroundSnails._
import com.fortysevendeg.scala.android.ui.components.{CircleView, RippleSnailData}
import macroid.Contexts
import macroid.FullDsl._

import scala.concurrent.ExecutionContext.Implicits.global

class RippleBackgroundActivity extends ActionBarActivity with Contexts[ActionBarActivity] with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    val rippleSnailData = RippleSnailData.fromPlace(rippleBackground.get)

    rippleBackground.map(_.setBackgroundColor(Color.RED))

    circle1.map(_.setColor(Color.RED))
    runUi(circle1 <~ On.click {
      anim(circle1, Color.RED)
    })

    circle2.map(_.setColor(Color.BLUE))
    runUi(circle2 <~ On.click {
      anim(circle2, Color.BLUE)
    })

    circle3.map(_.setColor(Color.GREEN))
    runUi(circle3 <~ On.click {
      anim(circle3, Color.GREEN)
    })

    toolBar map setSupportActionBar

    getSupportActionBar().setDisplayHomeAsUpEnabled(true)

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
            override def onAnimationStart(animation: Animator): Unit = {
              runUi(circleView <~ vInvisible)
            }
            override def onAnimationEnd(animation: Animator): Unit = {
              runUi(circleView <~ vVisible <~ vTransformation(0, 0))
            }
          })
        )
    (circleView <~~ move(rippleBackground)) ~~ (rippleBackground <~~ ripple(rippleData))

  }

}
