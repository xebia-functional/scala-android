package com.fortysevendeg.scala.android.ui.main

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.State
import android.view.View
import macroid.AppContext
import macroid.FullDsl._
import scala.language.postfixOps

class DividerItemDecorator(implicit appContext: AppContext) extends RecyclerView.ItemDecoration {

  override def getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State): Unit = {
    outRect.top = 4 dp;
    outRect.bottom = 0;
    outRect.left = 8 dp;
    outRect.right = 8 dp;
  }

}
