package com.fortysevendeg.scala.android.ui.circularreveal

import android.os.Bundle
import android.support.v4.app.{Fragment, FragmentActivity}
import android.support.v7.app.ActionBarActivity
import android.view.MenuItem
import com.fortysevendeg.scala.android.ui.components.IconTypes._
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawableTweaks._
import macroid.{Ui, Contexts}
import com.fortysevendeg.macroid.extras.FragmentExtras._
import macroid.FullDsl._

class InflatedViewActivity
  extends ActionBarActivity
  with Contexts[FragmentActivity]
  with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    toolBar map setSupportActionBar

    getSupportActionBar.setDisplayHomeAsUpEnabled(true)

  }

  def remove(fragment: Fragment): Unit = removeFragment(fragment)

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
