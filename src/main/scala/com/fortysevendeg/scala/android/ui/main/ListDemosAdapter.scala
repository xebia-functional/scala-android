package com.fortysevendeg.scala.android.ui.main

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.View.OnClickListener
import android.view.{View, ViewGroup}
import com.fortysevendeg.scala.android.R
import macroid.{ActivityContext, AppContext}
import macroid.FullDsl._
import com.fortysevendeg.scala.android.macroid.TextTweaks._
import com.fortysevendeg.scala.android.macroid.ViewTweaks._

class ListDemosAdapter(listener: RecyclerClickListener)
    (implicit context: ActivityContext, appContext: AppContext)
    extends RecyclerView.Adapter[ViewHolder] {

  val recyclerClickListener = listener

  val list = List(
    DemoInfo(
      "Ripple Background",
      "Description for Ripple Background",
      "RippleBackgroundActivity",
      21, 21),
    DemoInfo(
      "Texts Styles",
      "Description for Texts Styles",
      "TextStylesActivity",
      14, 14),
    DemoInfo(
      "Path Morphing",
      "Description for Path Morphing",
      "PathMorphingActivity",
      14, 14),
    DemoInfo(
      "Circular Reveal",
      "Description for Circular Reveal",
      "CircularRevealActivity",
      14, 21))

  override def onCreateViewHolder(parentViewGroup: ViewGroup, i: Int): ViewHolder = {
    val adapter = new Adapter
    adapter.layout.setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = recyclerClickListener.onClick(list(v.getTag.asInstanceOf[Int]))
    })
    return new ViewHolder(adapter)
  }

  override def getItemCount: Int = list.size

  override def onBindViewHolder(viewHolder: ViewHolder, position: Int): Unit = {
    val demoInfo = list(position)
    runUi(
      (viewHolder.content <~ vTag(position)) ~
          (viewHolder.title <~ tvText(demoInfo.name)) ~
          (viewHolder.description <~ tvText(demoInfo.description)) ~
          (viewHolder.api <~ tvText("API %d".format(demoInfo.minApi)) <~
              (if (Build.VERSION.SDK_INT < demoInfo.minApi) {
                vBackground(R.drawable.background_item_api_required)
              } else {
                vBackground(R.drawable.background_item_api_not_required)
              })
              )
    )

  }
}

trait RecyclerClickListener {
  def onClick(info: DemoInfo)
}

class ViewHolder(adapter: Adapter)(implicit context: ActivityContext, appContext: AppContext) extends RecyclerView.ViewHolder(adapter.layout) {

  var content = adapter.layout

  var title = adapter.title

  var description = adapter.description

  var api = adapter.api

}

case class DemoInfo(name: String, description: String, className: String, minApi: Int, targetApi: Int)
