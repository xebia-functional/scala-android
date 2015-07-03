package com.fortysevendeg.scala.android.ui.materiallist

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.{LayoutInflater, View, ViewGroup}
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.scala.android.ui.commons.AsyncImageTweaks._
import com.fortysevendeg.scala.android.{R, TR, TypedFindView}
import macroid.ActivityContextWrapper
import macroid.FullDsl._

class ImageListAdapter(implicit context: ActivityContextWrapper)
    extends RecyclerView.Adapter[ImageViewHolder]
    with View.OnClickListener {

  val images: Seq[ImageData] = 1 to 10 map {
    i =>
      ImageData(s"Item $i", s"http://lorempixel.com/500/500/animals/$i")
  }

  override def onCreateViewHolder(parent: ViewGroup, i: Int): ImageViewHolder = {
    val v = LayoutInflater.from(parent.getContext).inflate(R.layout.image_item, parent, false)
    v.setOnClickListener(this)
    new ImageViewHolder(v)
  }

  override def getItemCount: Int = images.size

  override def onBindViewHolder(viewHolder: ImageViewHolder, position: Int): Unit = {
    val image = images(position)
    runUi(
      (viewHolder.parent <~ vTag(position.toString)) ~
          (viewHolder.image <~ srcImage(image.url)) ~
          (viewHolder.text <~ tvText(image.name))
    )
  }

  override def onClick(v: View): Unit = {
    val image = images(v.getTag.toString.toInt)
    Snackbar.make(v, image.name, Snackbar.LENGTH_LONG).show()
  }
}

case class ImageViewHolder(parent: View)
    extends RecyclerView.ViewHolder(parent)
    with TypedFindView {

  val image = Option(findView(TR.image))
  val text = Option(findView(TR.text))

  override protected def findViewById(id: Int): View = parent.findViewById(id)

}

case class ImageData(name: String, url: String)

