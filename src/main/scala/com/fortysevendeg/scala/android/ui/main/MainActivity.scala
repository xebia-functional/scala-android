package com.fortysevendeg.scala.android.ui.main

import android.content.ComponentName
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.{GridLayoutManager, LinearLayoutManager}
import com.fortysevendeg.macroid.extras.DeviceMediaQueries._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import com.fortysevendeg.macroid.extras.ActionsExtras._
import com.fortysevendeg.scala.android.R
import macroid.Contexts
import macroid.FullDsl._

class MainActivity
  extends AppCompatActivity
  with Contexts[AppCompatActivity]
  with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    setContentView(layout)

    val adapter = new ProjectActivityInfoListAdapter(new RecyclerClickListener {
      override def onClick(info: ProjectActivityInfo): Unit = {
        if (info.apiType == APIType.REQUIRED) {
          aShortToast(getString(R.string.min_api_not_available))
        } else {
          aStartActivityFromComponentName(new ComponentName(getPackageName, info.className))
        }
      }
    })

    val layoutManager =
      landscapeTablet ?
          new GridLayoutManager(this, 3) |
          tablet ?
              new GridLayoutManager(this, 2) | new LinearLayoutManager(this)

    runUi(recyclerView <~ rvLayoutManager(layoutManager) <~
      rvAddItemDecoration(new MainItemDecorator) <~
      rvAdapter(adapter))

    toolBar map setSupportActionBar

  }

}
