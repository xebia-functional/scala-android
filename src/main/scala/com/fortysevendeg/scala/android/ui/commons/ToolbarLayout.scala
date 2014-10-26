package com.fortysevendeg.scala.android.ui.commons

import android.support.v7.widget.Toolbar
import macroid.{ActivityContext, AppContext}
import macroid.FullDsl._
import CommonsStyles._

trait ToolbarLayout {

  var toolBar = slot[Toolbar]

  def toolBarLayout(implicit appContext: AppContext, context: ActivityContext) =
    w[Toolbar] <~ wire(toolBar) <~ toolbarStyle

}
