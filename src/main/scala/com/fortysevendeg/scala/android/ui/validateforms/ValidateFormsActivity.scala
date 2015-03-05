package com.fortysevendeg.scala.android.ui.validateforms

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.MenuItem
import macroid.Contexts

class ValidateFormsActivity
  extends ActionBarActivity
  with Contexts[ActionBarActivity]
  with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

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
}
