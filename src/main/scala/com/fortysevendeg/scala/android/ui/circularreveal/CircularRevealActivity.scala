package com.fortysevendeg.scala.android.ui.circularreveal

import android.os.{Build, Bundle}
import android.support.v7.app.ActionBarActivity
import android.view.{View, MenuItem}
import macroid.Contexts
import macroid.FullDsl._
import com.fortysevendeg.scala.android.macroid.RevealSnails._
import scala.concurrent.ExecutionContext.Implicits.global

class CircularRevealActivity extends ActionBarActivity with Contexts[ActionBarActivity] with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    toolBar map setSupportActionBar

    getSupportActionBar().setDisplayHomeAsUpEnabled(true)

    runUi(circleButton <~ On.Click {
      anim
    })

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

  def anim = {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      if (content.get.getVisibility == View.VISIBLE) {
        content <~~ fadeOut(300)
      } else {
        content <~~ fadeIn(300)
      }
    } else {
      if (content.get.getVisibility == View.VISIBLE) {
        content <~~ hideCircularReveal
      } else {
        content <~~ showCircularReveal
      }
    }
  }

}
