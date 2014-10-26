package com.fortysevendeg.scala.android.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.{View, ViewGroup}
import android.widget.FrameLayout
import macroid.ActivityContext
import macroid.FullDsl._
import com.fortysevendeg.scala.android.macroid.ViewTweaks._

class RippleBackgroundView(context: Context, attr: AttributeSet, defStyleAttr: Int)(implicit acontext: ActivityContext)
    extends FrameLayout(context, attr, defStyleAttr) {

  def this(context: Context)(implicit acontext: ActivityContext) = this(context, null, 0)

  def this(context: Context, attr: AttributeSet)(implicit acontext: ActivityContext) = this(context, attr, 0)

  val rippleView = {
    var rippleView = slot[View]
    val ui = getUi(
      w[View] <~ wire(rippleView) <~ vInvisible
    )
    addView(ui, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    rippleView
  }

}



