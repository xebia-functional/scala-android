package com.fortysevendeg.scala.android.ui.pathmorphing

import android.graphics.Color
import android.view.View
import android.widget._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.macroid.extras.SeekBarEventsExtras.OnSeekBarChangeListenerHandler
import com.fortysevendeg.macroid.extras.SeekBarTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawableTweaks._
import com.fortysevendeg.scala.android.ui.components.Dim
import com.fortysevendeg.scala.android.ui.components.IconTypes._
import com.fortysevendeg.scala.android.ui.pathmorphing.Styles._
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext, Transformer}
import scala.language.postfixOps

trait Layout extends ToolbarLayout {

  val sizeOptionList = List(Dim(48, 48), Dim(72, 72), Dim(96, 96), Dim(128, 128))

  var icon = slot[ImageView]
  var sampleColor1Icon = slot[ImageView]
  var sampleColor2Icon = slot[ImageView]
  var sampleColor3Icon = slot[ImageView]
  var sampleColor4Icon = slot[ImageView]
  var iconSelectorGroup = slot[LinearLayout]
  var colorSelectorGroup = slot[LinearLayout]
  var strokeTitle = slot[TextView]
  var sizeTitle = slot[TextView]
  var strokeSelector = slot[SeekBar]
  var sizeSelector = slot[SeekBar]

  val deactivateImageViewWidgets = Transformer {
    case i: ImageView => i <~ vActivated(false)
  }

  val setNoIconImageViewWidgets = Transformer {
    case i: ImageView => i <~ pmdAnimIcon(NOICON)
  }

  def layout(implicit appContext: AppContext, context: ActivityContext) = {
    getUi(
      l[LinearLayout](
        toolBarLayout <~ tbTitle(R.string.title_path_morphing),
        l[LinearLayout](
          w[ImageView] <~ drawableStyle(width = 48 dp, height = 48 dp, stroke = 3 dp) <~ wire(icon),
          l[ScrollView](
            l[LinearLayout](
              w[TextView] <~ tvText(R.string.title_select_icon) <~ titleStyle,
              l[LinearLayout](
                l[LinearLayout](
                  w[ImageView] <~ iconSelectorStyle(BURGER, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(BURGER)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(CHECK, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(CHECK)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(ADD, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(ADD)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(CLOSE, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(CLOSE)) ~
                          (view <~ vActivated(true))
                    }
                  }
                ) <~ tableLayoutRowStyle,
                l[LinearLayout](
                  w[ImageView] <~ iconSelectorStyle(UP, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(UP)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(NEXT, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(NEXT)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(DOWN, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(DOWN)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(BACK, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(BACK)) ~
                          (view <~ vActivated(true))
                    }
                  }
                ) <~ tableLayoutRowStyle
              ) <~ tableLayoutStyle <~ wire(iconSelectorGroup),
              w[TextView] <~ tvText(R.string.title_select_color) <~ titleStyle,
              l[LinearLayout](
                l[LinearLayout](
                  w[ImageView] <~ colorSelectorStyle(true) <~ wire(sampleColor1Icon) <~ pmdColor(Color.WHITE) <~ vBackground(R.drawable.background_item_pathmorphsample_1) <~ On.click {
                    (icon <~ pmdColorResource(R.color.pathMorphSample1)) ~
                        (colorSelectorGroup <~ setNoIconImageViewWidgets) ~
                        (sampleColor1Icon <~ pmdAnimIcon(CHECK))
                  },
                  w[ImageView] <~ colorSelectorStyle(false) <~ wire(sampleColor2Icon) <~ vBackground(R.drawable.background_item_pathmorphsample_2) <~ On.click {
                    (icon <~ pmdColorResource(R.color.pathMorphSample2)) ~
                        (colorSelectorGroup <~ setNoIconImageViewWidgets) ~
                        (sampleColor2Icon <~ pmdAnimIcon(CHECK))
                  },
                  w[ImageView] <~ colorSelectorStyle(false) <~ wire(sampleColor3Icon) <~ vBackground(R.drawable.background_item_pathmorphsample_3) <~ On.click {
                    (icon <~ pmdColorResource(R.color.pathMorphSample3)) ~
                        (colorSelectorGroup <~ setNoIconImageViewWidgets) ~
                        (sampleColor3Icon <~ pmdAnimIcon(CHECK))
                  },
                  w[ImageView] <~ colorSelectorStyle(false) <~ wire(sampleColor4Icon) <~ pmdColor(Color.WHITE) <~ vBackground(R.drawable.background_item_pathmorphsample_4) <~ On.click {
                    (icon <~ pmdColorResource(R.color.pathMorphSample4)) ~
                        (colorSelectorGroup <~ setNoIconImageViewWidgets) ~
                        (sampleColor4Icon <~ pmdAnimIcon(CHECK))
                  }
                ) <~ tableLayoutRowStyle
              ) <~ tableLayoutStyle <~ wire(colorSelectorGroup),
              w[TextView] <~ wire(strokeTitle) <~ tvText(R.string.title_select_stroke) <~ titleStyle,
              w[SeekBar] <~ wire(strokeSelector) <~ strokeStyle <~ sbOnSeekBarChangeListener(
                OnSeekBarChangeListenerHandler(
                  onProgressChangedHandler = (view: SeekBar, progress: Int, fromUser: Boolean) => {
                    val stroke = progress + 1

                    (strokeTitle <~ tvText(context.get.getResources().getString(R.string.title_select_stroke, stroke.toString))) ~
                        (icon <~ pmdStroke(stroke dp))
                  }
                )
              ),
              w[TextView] <~ wire(sizeTitle) <~ tvText(R.string.title_select_size) <~ titleStyle,
              w[SeekBar] <~ wire(sizeSelector) <~ sizeStyle <~ sbOnSeekBarChangeListener(
                OnSeekBarChangeListenerHandler(
                  onStopTrackingTouchHandler = (view: SeekBar) => {
                    val Dim(width, height) = sizeOptionList(view.getProgress())

                    (sizeTitle <~ tvText(context.get.getResources().getString(R.string.title_select_size, width.toString, height.toString))) ~
                        (colorSelectorGroup <~ setNoIconImageViewWidgets) ~
                        (sampleColor1Icon <~ pmdAnimIcon(CHECK)) ~
                        (strokeSelector <~ sbProgress(2)) ~
                        (icon <~ drawableStyle(width dp, height dp, 3 dp) <~ pmdColorResource(R.color.pathMorphSample1)) ~
                        (iconSelectorGroup <~ deactivateImageViewWidgets)
                  }
                )
              )
            ) <~ verticalLinearLayoutStyle
          ) <~ scrollViewStyle
        ) <~ contentStyle
      ) <~ rootStyle
    )
  }

}