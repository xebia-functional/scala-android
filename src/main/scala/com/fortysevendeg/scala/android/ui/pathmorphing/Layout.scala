package com.fortysevendeg.scala.android.ui.pathmorphing

import android.graphics.Color
import android.view.View
import android.widget._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.macroid.ExtraSeekBarEvents.OnSeekBarChangeListenerHandler
import com.fortysevendeg.scala.android.macroid.SeekBarTweaks._
import com.fortysevendeg.scala.android.macroid.TextTweaks._
import com.fortysevendeg.scala.android.macroid.ToolbarTweaks._
import com.fortysevendeg.scala.android.macroid.ViewTweaks._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawableTweaks._
import com.fortysevendeg.scala.android.ui.components.{Dim, TypeIcons}
import com.fortysevendeg.scala.android.ui.pathmorphing.Styles._
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext, Transformer}

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
    case i: ImageView => i <~ pmdAnimIcon(TypeIcons.NOICON)
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
                  w[ImageView] <~ iconSelectorStyle(TypeIcons.BURGER, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(TypeIcons.BURGER)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(TypeIcons.CHECK, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(TypeIcons.CHECK)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(TypeIcons.ADD, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(TypeIcons.ADD)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(TypeIcons.CLOSE, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(TypeIcons.CLOSE)) ~
                          (view <~ vActivated(true))
                    }
                  }
                ) <~ tableLayoutRowStyle,
                l[LinearLayout](
                  w[ImageView] <~ iconSelectorStyle(TypeIcons.UP, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(TypeIcons.UP)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(TypeIcons.NEXT, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(TypeIcons.NEXT)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(TypeIcons.DOWN, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(TypeIcons.DOWN)) ~
                          (view <~ vActivated(true))
                    }
                  },
                  w[ImageView] <~ iconSelectorStyle(TypeIcons.BACK, false) <~ FuncOn.click {
                    (view: View) => {
                      (iconSelectorGroup <~ deactivateImageViewWidgets) ~
                          (icon <~ pmdAnimIcon(TypeIcons.BACK)) ~
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
                        (sampleColor1Icon <~ pmdAnimIcon(TypeIcons.CHECK))
                  },
                  w[ImageView] <~ colorSelectorStyle(false) <~ wire(sampleColor2Icon) <~ vBackground(R.drawable.background_item_pathmorphsample_2) <~ On.click {
                    (icon <~ pmdColorResource(R.color.pathMorphSample2)) ~
                        (colorSelectorGroup <~ setNoIconImageViewWidgets) ~
                        (sampleColor2Icon <~ pmdAnimIcon(TypeIcons.CHECK))
                  },
                  w[ImageView] <~ colorSelectorStyle(false) <~ wire(sampleColor3Icon) <~ vBackground(R.drawable.background_item_pathmorphsample_3) <~ On.click {
                    (icon <~ pmdColorResource(R.color.pathMorphSample3)) ~
                        (colorSelectorGroup <~ setNoIconImageViewWidgets) ~
                        (sampleColor3Icon <~ pmdAnimIcon(TypeIcons.CHECK))
                  },
                  w[ImageView] <~ colorSelectorStyle(false) <~ wire(sampleColor4Icon) <~ pmdColor(Color.WHITE) <~ vBackground(R.drawable.background_item_pathmorphsample_4) <~ On.click {
                    (icon <~ pmdColorResource(R.color.pathMorphSample4)) ~
                        (colorSelectorGroup <~ setNoIconImageViewWidgets) ~
                        (sampleColor4Icon <~ pmdAnimIcon(TypeIcons.CHECK))
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
                        (sampleColor1Icon <~ pmdAnimIcon(TypeIcons.CHECK)) ~
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