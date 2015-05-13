package com.fortysevendeg.scala.android.ui.googlemaps

import android.support.v4.app.{FragmentManager, Fragment}
import android.widget.{Button, LinearLayout}
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.FragmentExtras._
import com.google.android.gms.maps.GoogleMap
import macroid.FullDsl._
import macroid._

trait Layout
  extends ToolbarLayout
  with IdGeneration
  with Styles {

  val fragmentTag = "fragment-map"
  
  def layout(implicit context: ActivityContextWrapper, fragmentManager: FragmentManagerContext[Fragment, FragmentManager]) = {
    getUi(
      l[LinearLayout](
        toolBarLayout <~ tbTitle(R.string.title_map),
        l[LinearLayout](
          w[Button] <~ tvText(R.string.map_satellite) <~ buttonsStyle <~ On.click {
            Ui {
              findFragmentByTag[CustomMapFragment](fragmentTag) map (_.changeMapType(GoogleMap.MAP_TYPE_SATELLITE))
            }
          },
          w[Button] <~ tvText(R.string.map_normal) <~ buttonsStyle <~ On.click {
            Ui {
              findFragmentByTag[CustomMapFragment](fragmentTag) map (_.changeMapType(GoogleMap.MAP_TYPE_NORMAL))
            }
          },
          w[Button] <~ tvText(R.string.map_hybrid) <~ buttonsStyle <~ On.click {
            Ui {
              findFragmentByTag[CustomMapFragment](fragmentTag) map (_.changeMapType(GoogleMap.MAP_TYPE_HYBRID))
            }
          },
          w[Button] <~ tvText(R.string.map_terrain) <~ buttonsStyle <~ On.click {
            Ui {
              findFragmentByTag[CustomMapFragment](fragmentTag) map (_.changeMapType(GoogleMap.MAP_TYPE_TERRAIN))
            }
          }
        ) <~ horizontalLinearLayoutStyle,
        f[CustomMapFragmentImpl].framed(Id.map, fragmentTag)
      ) <~ contentStyle
    )
  }
}
