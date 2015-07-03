package com.fortysevendeg.scala.android.ui.materiallist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget._
import android.view.MenuItem
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import com.fortysevendeg.scala.android.{R, TR, TypedFindView}
import macroid.{Ui, Contexts}
import macroid.FullDsl._

class MaterialListActivity
  extends AppCompatActivity
  with TypedFindView
  with Transformations
  with Contexts[AppCompatActivity] {

  lazy val content = Option(findView(TR.content))

  lazy val toolBar = Option(findView(TR.toolbar))

  lazy val appBarLayout = Option(findView(TR.app_bar_layout))

  lazy val recycler = Option(findView(TR.recycler))

  lazy val fabActionButton = Option(findView(TR.fab_action_button))

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    toolBar foreach  setSupportActionBar
    getSupportActionBar.setDisplayHomeAsUpEnabled(true)

    val layoutManager = new GridLayoutManager(this, 2)

    runUi(
      (fabActionButton
          <~ fabIcon
          <~ On.click {
            Ui {
              content foreach {
                Snackbar.make(_, getString(R.string.material_list_add_item), Snackbar.LENGTH_LONG).show()
              }
            }
          }) ~
          (recycler
              <~ rvAdapter(new ImageListAdapter())
              <~ rvFixedSize
              <~ rvLayoutManager(layoutManager))
    )

  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = item.getItemId match {
    case android.R.id.home =>
      finish()
      false
    case _ => super.onOptionsItemSelected(item)
  }
}
