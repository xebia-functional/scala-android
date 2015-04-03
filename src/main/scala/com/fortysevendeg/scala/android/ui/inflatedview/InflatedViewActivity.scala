package com.fortysevendeg.scala.android.ui.inflatedview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarActivity
import android.view.MenuItem
import com.fortysevendeg.macroid.extras.FragmentExtras._
import com.fortysevendeg.scala.android.R
import macroid.Contexts

class InflatedViewActivity
  extends ActionBarActivity
  with Contexts[ActionBarActivity]
  with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.sample)

    initLayout
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
