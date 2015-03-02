package com.fortysevendeg.scala.android.ui.circularreveal

import android.os.Bundle
import android.support.v4.app.{FragmentManager, Fragment}
import android.support.v7.widget.CardView
import android.widget.{ImageView, TextView, FrameLayout, LinearLayout}
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid._
import com.fortysevendeg.macroid.extras.FragmentExtras._
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawableTweaks._
import com.fortysevendeg.scala.android.ui.components.IconTypes._

trait Layout
  extends ToolbarLayout
  with IdGeneration
  with Styles {

  val fragmentName = "sample-fragment"

  var circleButton = slot[ImageView]

  var content = slot[FrameLayout]

  def layout(implicit appContext: AppContext,
             context: ActivityContext,
             managerContext: FragmentManagerContext[Fragment, FragmentManager]) = getUi(
    l[LinearLayout](
      toolBarLayout <~ tbTitle(R.string.title_circular_reveal_styles),
      l[FrameLayout](
        l[FrameLayout](
        ) <~ wire(content) <~ id(Id.fragment) <~ fragmentStyle,
        w[ImageView] <~ wire(circleButton) <~ fabStyle <~ On.Click {
          findFragmentByTag[SampleFragment](fragmentName) match {
            case Some(f) =>
              Ui(f.unreveal()) ~
                (circleButton <~ pmdAnimIcon(ADD))
            case _ =>
              val (x: Int, y: Int) = (for {
                circle <- circleButton
                c <- content
              } yield (circle.getLeft - c.getLeft + circle.getWidth / 2,
                  circle.getTop - c.getTop + (circle.getHeight / 2))).getOrElse(0, 0)
              val args = new Bundle()
              args.putInt(SampleFragment.posX, x)
              args.putInt(SampleFragment.posY, y)
              addFragment(f[SampleFragment].pass(args), Some(Id.fragment), Some(fragmentName)) ~
                (circleButton <~ pmdAnimIcon(CLOSE))
          }
        }
      ) <~ contentStyle
    ) <~ rootStyle
  )

}

class FragmentLayout(implicit appContext: AppContext, context: ActivityContext)
  extends FragmentStyles {

  val content = getUi(
    l[CardView](
      l[LinearLayout](
        w[ImageView] <~ imageStyle,
        w[TextView] <~ textTitleStyle,
        w[TextView] <~ textMessageStyle
      ) <~ contentLayoutStyle
    ) <~ contentRevealStyle
  )

  def layout = content

}