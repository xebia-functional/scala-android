package com.fortysevendeg.scala.android.ui.materiallist

import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.v7.widget.{GridLayoutManager, RecyclerView}
import android.view.View
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.ui.commons.AsyncImageTweaks._
import com.fortysevendeg.scala.android.ui.components.IconTypes._
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawable
import com.fortysevendeg.scala.android.{R, TR, TypedFindView}
import macroid.FullDsl._
import macroid.{ActivityContextWrapper, ContextWrapper, Ui}

import scala.language.postfixOps

trait ScreenRenderer {

  self: TypedFindView =>

  lazy val content = Option(findView(TR.content))

  lazy val toolBar = Option(findView(TR.toolbar))

  lazy val appBarLayout = Option(findView(TR.app_bar_layout))

  lazy val recycler = Option(findView(TR.recycler))

  lazy val fabActionButton = Option(findView(TR.fab_action_button))

  def init(implicit contextWrapper: ActivityContextWrapper): Ui[_] = initRecycler ~ initFabButton

  private[this] def initRecycler(implicit contextWrapper: ActivityContextWrapper): Ui[_] =
    (recycler
        <~ rvAdapter(new ImageListAdapter())
        <~ rvFixedSize
        <~ rvLayoutManager(new GridLayoutManager(contextWrapper.bestAvailable, 2)))

  private[this] def initFabButton(implicit contextWrapper: ContextWrapper): Ui[_] =
    (fabActionButton
        <~ ivSrc(fabDrawable)
        <~ On.click {
      Ui {
        content foreach (Snackbar.make(_, resGetString(R.string.material_list_add_item), Snackbar.LENGTH_LONG).show())
      }
    })

  private[this] def fabDrawable(implicit contextWrapper: ContextWrapper) = new PathMorphDrawable(
    defaultIcon = ADD,
    defaultStroke = resGetDimensionPixelSize(R.dimen.circular_reveal_fab_stroke),
    defaultColor = Color.WHITE
  )

}

case class ImageViewHolder(parent: View)
    extends RecyclerView.ViewHolder(parent)
    with TypedFindView {

  lazy val image = Option(findView(TR.image))
  lazy val text = Option(findView(TR.text))

  override protected def findViewById(id: Int): View = parent.findViewById(id)

  def bind(imageData: ImageData, position: Int)(implicit contextWrapper: ActivityContextWrapper): Ui[_] =
    (parent <~ vTag(position.toString)) ~
        (image <~ srcImage(imageData.url)) ~
        (text <~ tvText(imageData.name))

}
