package com.fortysevendeg.scala.android.ui.pathmorphing

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.MenuItem
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.macroid.SeekBarTweaks._
import com.fortysevendeg.scala.android.macroid.TextTweaks._
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawableTweaks._
import com.fortysevendeg.scala.android.ui.components.TypeIcons
import macroid.Contexts
import macroid.FullDsl._

class PathMorphingActivity extends ActionBarActivity with Contexts[ActionBarActivity] with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    toolBar map setSupportActionBar

    getSupportActionBar().setDisplayHomeAsUpEnabled(true)

    runUi(
      (icon <~ pmdAnimIcon(TypeIcons.BURGER)) ~
          (strokeSelector <~ sbProgress(2)) ~
          (sizeTitle <~ tvText(getApplicationContext().getString(R.string.title_select_size, 48.toString, 48.toString)))
    )
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