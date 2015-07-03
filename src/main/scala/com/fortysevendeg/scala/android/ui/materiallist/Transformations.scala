package com.fortysevendeg.scala.android.ui.materiallist

import android.graphics.Color
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.components.IconTypes._
import com.fortysevendeg.scala.android.ui.components.PathMorphDrawable
import macroid.ContextWrapper

import scala.language.postfixOps

trait Transformations {

  def fabIcon(implicit contextWrapper: ContextWrapper) =
    ivSrc(new PathMorphDrawable(
      defaultIcon = ADD,
      defaultStroke = resGetDimensionPixelSize(R.dimen.circular_reveal_fab_stroke),
      defaultColor = Color.WHITE
    ))

}

