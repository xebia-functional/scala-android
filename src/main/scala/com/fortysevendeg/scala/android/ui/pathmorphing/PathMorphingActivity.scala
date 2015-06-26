package com.fortysevendeg.scala.android.ui.pathmorphing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.macroid.extras.SeekBarTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawableTweaks._
import com.fortysevendeg.scala.android.ui.components.IconTypes._
import macroid.Contexts
import macroid.FullDsl._

class PathMorphingActivity
  extends AppCompatActivity
  with Contexts[AppCompatActivity]
  with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    toolBar foreach setSupportActionBar

    getSupportActionBar.setDisplayHomeAsUpEnabled(true)

    runUi(
      (icon <~ pmdAnimIcon(BURGER)) ~
          (strokeSelector <~ sbProgress(2)) ~
          (sizeTitle <~ tvText(getString(R.string.title_select_size, 48.toString, 48.toString)))
    )
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = item.getItemId match {
    case android.R.id.home =>
      finish()
      false
    case _ => super.onOptionsItemSelected(item)
  }
}