package com.fortysevendeg.scala.android.ui.ripplebackground

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.MenuItem
import com.fortysevendeg.scala.android.macroid.RippleSnailData
import com.fortysevendeg.scala.android.ui.components.{RippleBackgroundView, CircleView}
import macroid.FullDsl._
import macroid.Contexts
import com.fortysevendeg.scala.android.macroid.RevealSnails._
import macroid.util.Ui

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class RippleBackgroundActivity extends ActionBarActivity with Contexts[ActionBarActivity] with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    val rippleSnailData = RippleSnailData.fromPlace(rippleBackground.get)

    rippleBackground.map(_.setBackgroundColor(Color.RED))

    circle1.map(_.setColor(Color.RED))
    runUi(circle1 <~ On.click {
      rippleBackground <~~ ripple(rippleSnailData.copy(resColor = Color.RED))
    })

    val a2 = rippleBackground <~~ ripple(rippleSnailData.copy(resColor = Color.BLUE))

    circle2.map(_.setColor(Color.BLUE))
    runUi(circle2 <~ On.click {
      a2
    })

    val moveCircleToViewSNail = circle3 <~~ move(rippleBackground.get)

    val rippleBackgroundAnimation = rippleBackground <~~ ripple(rippleSnailData.copy(resColor = Color.GREEN))

    val a3 = moveCircleToViewSNail ~ rippleBackgroundAnimation

    circle3.map(_.setColor(Color.GREEN))
    runUi(circle3 <~ On.click {
      a3
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

}
