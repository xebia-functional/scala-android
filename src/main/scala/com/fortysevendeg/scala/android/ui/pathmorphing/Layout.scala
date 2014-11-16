package com.fortysevendeg.scala.android.ui.pathmorphing

import android.view.Gravity
import android.widget._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.macroid.FrameLayoutTweaks._
import com.fortysevendeg.scala.android.macroid.ImageViewTweaks._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import Styles._
import com.fortysevendeg.scala.android.ui.components.{TypeIcons, PathMorphDrawable}
import macroid.{Ui, ActivityContext, AppContext}
import macroid.FullDsl._
import com.fortysevendeg.scala.android.macroid.ToolbarTweaks._
import com.fortysevendeg.scala.android.macroid.TextTweaks._
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawableTweaks._

trait Layout extends ToolbarLayout {

  var icon = slot[ImageView]

  def layout(implicit appContext: AppContext, context: ActivityContext) = {
    getUi(
      l[LinearLayout](
        toolBarLayout <~ tbTitle(R.string.title_path_morphing),
        l[FrameLayout](
          l[LinearLayout](
            l[RadioGroup](
              w[RadioButton] <~ tvText("Burger") + radioItemStyle <~ On.click {
                icon <~ animIcon(TypeIcons.BURGER)
              },
              w[RadioButton] <~ tvText("Back") <~ radioItemStyle <~ On.click {
                icon <~ animIcon(TypeIcons.BACK)
              },
              w[RadioButton] <~ tvText("Check") <~ radioItemStyle <~ On.click {
                icon <~ animIcon(TypeIcons.CHECK)
              },
              w[RadioButton] <~ tvText("Add") <~ radioItemStyle <~ On.click {
                icon <~ animIcon(TypeIcons.ADD)
              }
            ) <~ contentItemsRadiosStyle,
            l[RadioGroup](
              w[RadioButton] <~ tvText("48x48") + radioItemStyle <~ On.click {
                icon <~ flContentSize(48 dp, 48 dp) <~ flLayoutGravity(Gravity.CENTER) + ivSrc(new PathMorphDrawable())
              },
              w[RadioButton] <~ tvText("72x72") <~ radioItemStyle <~ On.click {
                icon <~ flContentSize(72 dp, 72 dp) <~ flLayoutGravity(Gravity.CENTER) + ivSrc(new PathMorphDrawable())
              },
              w[RadioButton] <~ tvText("94x94") <~ radioItemStyle <~ On.click {
                icon <~ flContentSize(94 dp, 94 dp) <~ flLayoutGravity(Gravity.CENTER) + ivSrc(new PathMorphDrawable())
              },
              w[RadioButton] <~ tvText("128x128") <~ radioItemStyle <~ On.click {
                icon <~ flContentSize(128 dp, 128 dp) <~ flLayoutGravity(Gravity.CENTER) + ivSrc(new PathMorphDrawable())
              }
            ) <~ contentItemsRadiosStyle
          ) <~ contentRadiosStyle,
          w[ImageView] <~ drawableStyle <~ wire(icon)
        ) <~ contentStyle
      ) <~ rootStyle
    )
  }

}
