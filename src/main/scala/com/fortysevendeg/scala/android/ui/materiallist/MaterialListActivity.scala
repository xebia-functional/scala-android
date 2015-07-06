package com.fortysevendeg.scala.android.ui.materiallist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.fortysevendeg.scala.android.{R, TypedFindView}
import macroid.Contexts
import macroid.FullDsl._

class MaterialListActivity
  extends AppCompatActivity
  with TypedFindView
  with MainComposer
  with Contexts[AppCompatActivity] {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    toolBar foreach setSupportActionBar
    getSupportActionBar.setDisplayHomeAsUpEnabled(true)

    runUi(composition)
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = item.getItemId match {
    case android.R.id.home =>
      finish()
      false
    case _ => super.onOptionsItemSelected(item)
  }
}
