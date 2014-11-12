package com.fortysevendeg.scala.android.ui.pathmorphing

import android.animation.{Animator, AnimatorListenerAdapter}
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.MenuItem
import com.fortysevendeg.scala.android.macroid.RevealSnails._
import com.fortysevendeg.scala.android.macroid.ViewTweaks._
import com.fortysevendeg.scala.android.ui.components.RippleBackgroundSnails._
import com.fortysevendeg.scala.android.ui.components.{TypeIcons, CircleView, RippleSnailData}
import macroid.{Ui, Contexts}
import macroid.FullDsl._

import scala.concurrent.ExecutionContext.Implicits.global

class PathMorphingActivity extends ActionBarActivity with Contexts[ActionBarActivity] with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

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
