package com.fortysevendeg.scala.android.ui.circularreveal

import android.animation.{AnimatorListenerAdapter, ObjectAnimator, Animator}
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.animation.{AccelerateInterpolator, DecelerateInterpolator}
import android.view.{ViewAnimationUtils, View, ViewGroup, LayoutInflater}
import com.fortysevendeg.macroid.extras.SnailsUtils
import com.fortysevendeg.macroid.extras.DeviceVersion._
import macroid.Contexts

class SampleFragment
  extends Fragment
  with Contexts[Fragment] {

  private var lastWidth: Option[Int] = None

  private var lastHeight: Option[Int] = None

  private var lastRevealX: Option[Int] = None

  private var lastRevealY: Option[Int] = None

  private var fragmentLayout: Option[FragmentLayout] = None

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
        CurrentVersion match {
          case version if version >= Lollipop =>
            getLollipopReveal(fLayout.layout, width, height)
          case _ =>
            ltLollipopReveal(fLayout.layout)
        }
    }
  }

  def unreveal(): Unit = {
    fragmentLayout.map {
      fLayout =>
        CurrentVersion match {
          case version if version >= Lollipop =>
            getLollipopUnreveal(fLayout.layout)
          case _ =>
            ltLollipopUnreveal(fLayout.layout)
        }
    }
  }

  def getLollipopReveal(layout: View, width: Int, height: Int) = {
    val cx = getArguments.getInt(SampleFragment.posX, 0)
    val cy = getArguments.getInt(SampleFragment.posY, 0)
    lastRevealX = Some(cx)
    lastRevealY = Some(cy)
    val endRadius = SnailsUtils.calculateRadius(cx, cy, width, height)
    val reveal: Animator = ViewAnimationUtils.createCircularReveal(layout, cx, cy, 0, endRadius)
    reveal.setInterpolator(new DecelerateInterpolator(2f))
    reveal.start()
  }

  def getLollipopUnreveal(layout: View) = {
    val (x, y, w, h) = (for {
      x <- lastRevealX
      y <- lastRevealY
      w <- lastWidth
      h <- lastHeight
    } yield (x, y, w, h)) getOrElse(0, 0, 0, 0)
    val radius = SnailsUtils.calculateRadius(x, y, w, h)
    val reveal: Animator = ViewAnimationUtils.createCircularReveal(layout, x, y, radius, 0)
    reveal.setInterpolator(new DecelerateInterpolator(2f))
    reveal.addListener(new AnimatorListenerAdapter {
      override def onAnimationEnd(animation: Animator): Unit = {
        super.onAnimationEnd(animation)
        layout.setVisibility(View.GONE)
        getActivity.asInstanceOf[CircularRevealActivity].remove(SampleFragment.this)
      }
    })
    reveal.start()
  }

  def ltLollipopReveal(layout: View) = {
    val alpha: Animator = ObjectAnimator.ofFloat(layout, "alpha", 0f, 1)
    alpha.setInterpolator(new DecelerateInterpolator(2f))
    alpha.start()
  }

  def ltLollipopUnreveal(layout: View) = {
    val alpha: Animator = ObjectAnimator.ofFloat(layout, "alpha", 1f, 0f)
    alpha.setInterpolator(new AccelerateInterpolator(2f))
    alpha.addListener(new AnimatorListenerAdapter {
      override def onAnimationEnd(animation: Animator): Unit = {
        super.onAnimationEnd(animation)
        layout.setVisibility(View.GONE)
        getActivity.asInstanceOf[CircularRevealActivity].remove(SampleFragment.this)
      }
    })
    alpha.start()
  }

}

object SampleFragment {

  val posX = "pos_x"
  val posY = "pos_y"

}
