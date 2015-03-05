package com.fortysevendeg.scala.android.ui.main

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.State
import android.view.View
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.scala.android.R
import macroid.AppContext

import scala.language.postfixOps

class MainItemDecorator(implicit appContext: AppContext)
  extends RecyclerView.ItemDecoration {

  override def getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State): Unit = {
    outRect.top = resGetDimensionPixelSize(R.dimen.padding_default)
    outRect.bottom = resGetDimensionPixelSize(R.dimen.padding_default)
    outRect.left = resGetDimensionPixelSize(R.dimen.padding_default)
    outRect.right = resGetDimensionPixelSize(R.dimen.padding_default)
  }

}
