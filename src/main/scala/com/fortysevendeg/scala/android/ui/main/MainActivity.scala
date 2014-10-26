package com.fortysevendeg.scala.android.ui.main

import android.os.{Build, Bundle}
import android.support.v7.app.ActionBarActivity
import android.support.v7.widget.LinearLayoutManager
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.ripplebackground.RippleBackgroundActivity
import com.fortysevendeg.scala.android.ui.textstyles.TextStylesActivity
import macroid.util.Ui
import macroid.{ActivityContext, AppContext, Contexts}
import macroid.FullDsl._
import com.fortysevendeg.scala.android.macroid.ExtraActions._


class MainActivity
    extends ActionBarActivity
    with Contexts[ActionBarActivity]
    with Layout {

  val layoutManager = new LinearLayoutManager(this)

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    setContentView(layout)

    val adapter = new ListDemosAdapter(new RecyclerClickListener {
      override def onClick(info: DemoInfo): Unit = {
        if (Build.VERSION.SDK_INT >= info.minApi) {
          // TODO that's a mamarracho. I try use Class.from() and it was impossible
          info match {
            case DemoInfo(_, _, "RippleBackgroundActivity", _) => aStartActivity[RippleBackgroundActivity]
            case DemoInfo(_, _, "TextStylesActivity", _) => aStartActivity[TextStylesActivity]
          }
        } else {
          aShortToast(getString(R.string.min_api_not_available))
        }
      }
    })

    recyclerView.map(view => {
      view.setLayoutManager(layoutManager)
      view.setAdapter(adapter)
    })

    toolBar map setSupportActionBar

  }

}
