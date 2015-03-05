package com.fortysevendeg.scala.android.ui.commons

import android.support.v7.widget.Toolbar
import android.view.ContextThemeWrapper
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.commons.CommonsStyles._
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext, Ui}

trait ToolbarLayout {

  var toolBar = slot[Toolbar]

  def toolBarLayout(implicit appContext: AppContext, activityContext: ActivityContext): Ui[Toolbar] =
    Ui {
      val darkToolBar = getToolbarThemeDarkActionBar
      toolBar = Some(darkToolBar)
      darkToolBar
    } <~ toolbarStyle

  private def getToolbarThemeDarkActionBar(implicit activityContext: ActivityContext) = {
    val contextTheme = new ContextThemeWrapper(activityContext.get, R.style.ThemeOverlay_AppCompat_Dark_ActionBar)
    val darkToolBar = new Toolbar(contextTheme)
    darkToolBar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light)
    darkToolBar
  }

}
