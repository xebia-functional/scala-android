package com.fortysevendeg.scala.android.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View.OnClickListener
import android.view.{View, ViewGroup}
import android.widget.TextView
import com.fortysevendeg.scala.android.R
import macroid.{AppContext, IdGeneration, ActivityContext}

class ListDemosAdapter(listener: RecyclerClickListener)
    (implicit context: ActivityContext, appContext: AppContext)
    extends RecyclerView.Adapter[ViewHolder] {

  val recyclerClickListener = listener

  val list = List(
    DemoInfo(
      "Ripple Background",
      "Description for Ripple Background",
      "RippleBackgroundActivity",
      21),
    DemoInfo(
      "Texts Styles",
      "Description for Texts Styles",
      "TextStylesActivity",
      14),
    DemoInfo(
      "Circular Reveal",
      "Description for Circular Reveal",
      "CircularRevealActivity",
      21))

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
    viewHolder.content.setTag(position)
    viewHolder.title.map(_.setText(demoInfo.name))
    viewHolder.description.map(_.setText(demoInfo.description))
    viewHolder.api.map(_.setText("API %d".format(demoInfo.minApi)))
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

case class DemoInfo(name: String, description: String, className: String, minApi: Int)
