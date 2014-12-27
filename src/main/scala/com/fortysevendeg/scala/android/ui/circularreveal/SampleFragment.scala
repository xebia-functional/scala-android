package com.fortysevendeg.scala.android.ui.circularreveal

import android.animation.{AnimatorListenerAdapter, ObjectAnimator, Animator}
import android.os.{Build, Bundle}
import android.support.v4.app.Fragment
import android.view.animation.{AccelerateInterpolator, DecelerateInterpolator}
import android.view.{ViewAnimationUtils, View, ViewGroup, LayoutInflater}
import com.fortysevendeg.macroid.extras.SnailsUtils
import macroid.Contexts

class SampleFragment extends Fragment with Contexts[Fragment] {

  var lastWidth: Option[Int] = None

  var lastHeight: Option[Int] = None

  var lastRevealX: Option[Int] = None

  var lastRevealY: Option[Int] = None

  var fragmentLayout: Option[FragmentLayout] = None

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {

    val fLayout = new FragmentLayout

    fragmentLayout = Some(fLayout)

    fLayout.layout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
      override def onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int): Unit = {
        v.removeOnLayoutChangeListener(this)
        reveal(right - left, bottom - top)
      }
    })

    fLayout.layout

  }

  def reveal(width: Int, height: Int) = {
    lastWidth = Some(width)
    lastHeight = Some(height)
    fragmentLayout.map {
      fLayout =>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          val cx = getArguments().getInt(SampleFragment.POS_X, 0)
          val cy = getArguments().getInt(SampleFragment.POS_Y, 0)
          lastRevealX = Some(cx)
          lastRevealY = Some(cy)
          val endRadius = SnailsUtils.calculateRadius(cx, cy, width, height)
          val reveal: Animator = ViewAnimationUtils.createCircularReveal(fLayout.layout, cx, cy, 0, endRadius)
          reveal.setInterpolator(new DecelerateInterpolator(2f))
          reveal.start()
        } else {
          val alpha: Animator = ObjectAnimator.ofFloat(fLayout.layout, "alpha", 0f, 1)
          alpha.setInterpolator(new DecelerateInterpolator(2f))
          alpha.start()
        }
    }
  }

  def unreveal(): Unit = {
    for {
      fLayout <- fragmentLayout
      x <- lastRevealX
      y <- lastRevealY
      w <- lastWidth
      h <- lastHeight
    } yield {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val radius = SnailsUtils.calculateRadius(x, y, w, h)
        val reveal: Animator = ViewAnimationUtils.createCircularReveal(fLayout.layout, x, y, radius, 0)
        reveal.setInterpolator(new DecelerateInterpolator(2f))
        reveal.addListener(new AnimatorListenerAdapter {
          override def onAnimationEnd(animation: Animator): Unit = {
            super.onAnimationEnd(animation)
            fragmentLayout.get.layout.setVisibility(View.GONE)
            getActivity.asInstanceOf[CircularRevealActivity].remove(SampleFragment.this)
          }
        })
        reveal.start()
      } else {
        val alpha: Animator = ObjectAnimator.ofFloat(fLayout.layout, "alpha", 1f, 0f)
        alpha.setInterpolator(new AccelerateInterpolator(2f))
        alpha.start()
      }
    }
  }

}

object SampleFragment {

  val POS_X = "pos_x"
  val POS_Y = "pos_y"

}
