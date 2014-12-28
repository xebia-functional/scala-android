package com.fortysevendeg.scala.android.ui.circularreveal

import android.os.Bundle
import android.support.v4.app.{FragmentManager, Fragment}
import android.widget.{TextView, FrameLayout, LinearLayout}
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.scala.android.ui.circularreveal.Styles._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import com.fortysevendeg.scala.android.ui.components.CircleButton
import macroid.FullDsl._
import macroid.{IdGeneration, FragmentManagerContext, ActivityContext, AppContext}
import com.fortysevendeg.macroid.extras.ExtraFragment._

trait Layout extends ToolbarLayout with IdGeneration {

  val fragmentName = "name"

  var circleButton = slot[CircleButton]

  var content = slot[LinearLayout]

  def layout(implicit appContext: AppContext,
      context: ActivityContext,
      managerContext: FragmentManagerContext[Fragment, FragmentManager]) = getUi(
    l[LinearLayout](
      toolBarLayout <~ tbTitle(R.string.title_circular_reveal_styles),
      l[FrameLayout](
        w[CircleButton] <~ wire(circleButton) <~ fabStyle <~ On.Click {
          val (x: Int, y: Int) = (for {
            circle <- circleButton
            c <- content
          } yield (
                (circle.getLeft - c.getLeft + (circle.getWidth / 2),
                    circle.getTop - c.getTop + (circle.getHeight / 2))
                )).getOrElse(0, 0)
          val args = new Bundle()
          args.putInt(SampleFragment.posX, x)
          args.putInt(SampleFragment.posY, y)
          addFragment(f[SampleFragment], Some(args), Some(Id.fragment), Some(fragmentName))
        },
        l[LinearLayout](
        ) <~ wire(content) <~ id(Id.fragment)
      ) <~ contentStyle
    ) <~ rootStyle
  )

}

class FragmentLayout(implicit appContext: AppContext, context: ActivityContext) {

  val content = getUi(
    l[LinearLayout](
      w[TextView] <~ textStyle
    ) <~ contentRevealStyle
  )

  def layout = content

}