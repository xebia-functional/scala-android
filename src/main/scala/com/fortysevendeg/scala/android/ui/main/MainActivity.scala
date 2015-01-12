package com.fortysevendeg.scala.android.ui.main

import android.content.ComponentName
import android.os.{Build, Bundle}
import android.support.v7.app.ActionBarActivity
import android.support.v7.widget.{GridLayoutManager, LinearLayoutManager}
import com.fortysevendeg.macroid.extras.DevicesQueries._
import com.fortysevendeg.macroid.extras.ExtraActions._
import com.fortysevendeg.scala.android.R
import macroid.Contexts
import macroid.FullDsl._

class MainActivity
    extends ActionBarActivity
    with Contexts[ActionBarActivity]
    with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    setContentView(layout)

    val adapter = new ListDemosAdapter(new RecyclerClickListener {
      override def onClick(info: ProjectActivityInfo): Unit = {
        if (Build.VERSION.SDK_INT >= info.minApi) {
          aStartActivityFromComponentName(new ComponentName(getPackageName(), info.className))
        } else {
          aShortToast(getString(R.string.min_api_not_available))
        }
      }
    })

    val layoutManager =
      landscapeTablet ?
          new GridLayoutManager(this, 4) |
          tablet ?
              new GridLayoutManager(this, 3) | new LinearLayoutManager(this)

    recyclerView.map(view => {
      view.setLayoutManager(layoutManager)
      view.setAdapter(adapter)
      view.addItemDecoration(new DividerItemDecorator)
    })

    toolBar map setSupportActionBar

  }

}
