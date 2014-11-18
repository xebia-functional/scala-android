package com.fortysevendeg.scala.android.macroid

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarActivity
import android.support.v7.widget.{CardView, RecyclerView, Toolbar}
import android.util.TypedValue
import android.view.ViewGroup.LayoutParams._
import android.view.{View, ViewGroup}
import android.widget.{ImageView, FrameLayout, LinearLayout, TextView}
import macroid.FullDsl._
import macroid.{AppContext, ActivityContext, Tweak}

object ViewTweaks {
  type W = View

  private def lp(w: Int, h: Int) = Tweak[W](_.setLayoutParams(new ViewGroup.LayoutParams(w, h)))

  val vMatchParent = lp(MATCH_PARENT, MATCH_PARENT)
  val vWrapContent = lp(WRAP_CONTENT, WRAP_CONTENT)
  val vMatchWidth = lp(MATCH_PARENT, WRAP_CONTENT)
  val vMatchHeight = lp(WRAP_CONTENT, MATCH_PARENT)

  def vContentSize(w: Int, h: Int) = lp(w, h)

  def vContentSizeMatchWidth(h: Int) = lp(MATCH_PARENT, h)

  def vContentSizeMatchHeight(w: Int) = lp(w, MATCH_PARENT)

  def vMargins(
      margin: Int) = Tweak[W] {
    view =>
      view.getLayoutParams.asInstanceOf[ViewGroup.MarginLayoutParams].setMargins(margin, margin, margin, margin)
      view.requestLayout
  }

  def vMargin(
      marginLeft: Int = 0,
      marginTop: Int = 0,
      marginRight: Int = 0,
      marginBottom: Int = 0) = Tweak[W] {
    view =>
      view.getLayoutParams.asInstanceOf[ViewGroup.MarginLayoutParams].setMargins(marginLeft, marginTop, marginRight, marginBottom)
      view.requestLayout
  }

  def vPaddings(padding: Int) = Tweak[W](_.setPadding(padding, padding, padding, padding))

  def vPadding(
      paddingLeft: Int = 0,
      paddingTop: Int = 0,
      paddingRight: Int = 0,
      paddingBottom: Int = 0) = Tweak[W](_.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom))

  def vBackground(res: Int) = Tweak[W](_.setBackgroundResource(res))
  def vBackgroundColor(color: Int) = Tweak[W](_.setBackgroundColor(color))
  def vBackground(drawable: Drawable) = Tweak[W](_.setBackground(drawable))

  def vTag(tag: String) = Tweak[W](_.setTag(tag))

  def vTag(tag: Int) = Tweak[W](_.setTag(tag))

  def vTransformation(x: Int = 0, y: Int = 0) = Tweak[W] {
    view =>
      view.setTranslationX(x)
      view.setTranslationY(y)
  }

  val vGone = Tweak[View](_.setVisibility(View.GONE))
  val vVisible = Tweak[View](_.setVisibility(View.VISIBLE))
  val vInvisible = Tweak[View](_.setVisibility(View.INVISIBLE))

}

object ImageViewTweaks {
  type W = ImageView

  def ivSrc(drawable: Drawable) = Tweak[W](_.setImageDrawable(drawable))

}

object LinearLayoutTweaks {
  type W = LinearLayout

  val llHorizontal = Tweak[W](_.setOrientation(LinearLayout.HORIZONTAL))
  val llVertical = Tweak[W](_.setOrientation(LinearLayout.VERTICAL))
  val llMatchWeightVertical = lp[W](MATCH_PARENT, 0, 1)
  val llMatchWeightHorizontal = lp[W](0, MATCH_PARENT, 1)
  val llWrapWeightVertical = lp[W](WRAP_CONTENT, 0, 1)
  val llWrapWeightHorizontal = lp[W](0, WRAP_CONTENT, 1)

  def llGravity(gravity: Int) = Tweak[W](_.setGravity(gravity))

  def llLayoutGravity(gravity: Int) = Tweak[View] {
    view =>
      val param = new LinearLayout.LayoutParams(view.getLayoutParams.width, view.getLayoutParams.height)
      param.gravity = gravity
      view.setLayoutParams(param)
  }
}

object FrameLayoutTweaks {
  type W = FrameLayout

  def flContentSize(w: Int, h: Int) = lp[W](w, h)

  val flMatchWeightVertical = lp[W](MATCH_PARENT, 0, 1)
  val flMatchWeightHorizontal = lp[W](0, MATCH_PARENT, 1)

  def flLayoutGravity(gravity: Int) = Tweak[View] {
    view =>
      val param = new FrameLayout.LayoutParams(view.getLayoutParams.width, view.getLayoutParams.height)
      param.gravity = gravity
      view.setLayoutParams(param)
  }

}

object RecyclerViewTweaks {
  type W = RecyclerView

  val fixedSize = Tweak[W](_.setHasFixedSize(true))
}

object CardViewTweaks {
  type W = CardView

  def cvRadius(radius: Float) = Tweak[W](_.setRadius(radius))
}

object TextTweaks {
  type W = TextView

  def tvColor(color: Int) = Tweak[W](_.setTextColor(color))

  val tvBold = Tweak[W](x ⇒ x.setTypeface(x.getTypeface, Typeface.BOLD))
  val tvItalic = Tweak[W](x ⇒ x.setTypeface(x.getTypeface, Typeface.ITALIC))
  val tvBoldItalic = Tweak[W](x ⇒ x.setTypeface(x.getTypeface, Typeface.BOLD_ITALIC))

  val tvNormalLight = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL)))
  val tvBoldLight = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-light", Typeface.BOLD)))
  val tvItalicLight = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-light", Typeface.ITALIC)))
  val tvBoldItalicLight = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-light", Typeface.BOLD_ITALIC)))

  val tvNormalCondensed = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL)))
  val tvBoldCondensed = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD)))
  val tvItalicCondensed = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-condensed", Typeface.ITALIC)))
  val tvBoldItalicCondensed = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD_ITALIC)))

  def tvSize(points: Int) = Tweak[W](_.setTextSize(TypedValue.COMPLEX_UNIT_SP, points))

  def tvLines(lines: Int) = Tweak[W](_.setLines(lines))

  def tvMaxLines(lines: Int) = Tweak[W](_.setMaxLines(lines))

  def tvGravity(gravity: Int) = Tweak[W](_.setGravity(gravity))

  def tvText(text: String) = Tweak[W](_.setText(text))

  def tvText(text: Int) = Tweak[W](_.setText(text))

}

object ToolbarTweaks {
  type W = Toolbar

  def tbTitle(title: String) = Tweak[W](_.setTitle(title))

  def tbTitle(title: Int) = Tweak[W](_.setTitle(title))

}

