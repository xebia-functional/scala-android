package com.fortysevendeg.scala.android.ui.ripplebackground

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import com.fortysevendeg.scala.android.macroid.RippleSnailData
import macroid.FullDsl._
import macroid.Contexts
import com.fortysevendeg.scala.android.macroid.RevealSnails._

import scala.concurrent.ExecutionContext.Implicits.global

class RippleBackgroundActivity extends ActionBarActivity with Contexts[ActionBarActivity] with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    rippleBackground.map(_.setBackgroundColor(Color.RED))

    circle1.map(_.setColor(Color.RED))
    runUi(circle1 <~ On.click {
      rippleBackground <~~ ripple(RippleSnailData.fromPlace(rippleBackground.get).copy(resColor = Color.RED))
    })

    circle2.map(_.setColor(Color.BLUE))
    runUi(circle2 <~ On.click {
      rippleBackground <~~ ripple(RippleSnailData.fromPlace(rippleBackground.get).copy(resColor = Color.BLUE))
    })

    circle3.map(_.setColor(Color.GREEN))
    runUi(circle3 <~ On.click {
      rippleBackground <~~ ripple(RippleSnailData.fromPlace(rippleBackground.get).copy(resColor = Color.GREEN))
    })

  }

}
