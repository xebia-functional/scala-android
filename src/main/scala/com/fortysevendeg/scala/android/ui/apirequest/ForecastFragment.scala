package com.fortysevendeg.scala.android.ui.apirequest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{LayoutInflater, View, ViewGroup}
import com.fortysevendeg.macroid.extras.TextTweaks._
import macroid.Contexts
import macroid.FullDsl._

class ForecastFragment extends Fragment with Contexts[Fragment] {

  private var fragmentLayout: Option[ForecastFragmentLayout] = None

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {

    val fLayout = new ForecastFragmentLayout

    fragmentLayout = Some(fLayout)

    fLayout.layout

  }

  override def onViewCreated(view: View, savedInstanceState: Bundle) = {
    super.onViewCreated(view, savedInstanceState)
    
    import ForecastFragment._
    
    for {
      bundle <- Option(getArguments)
      layout <- fragmentLayout
    } yield runUi(
      layout.textView <~ tvText(s"Location: ${bundle.getDouble(latitudeKey)},${bundle.getDouble(longitudeKey)}"))
    
  }

}

object ForecastFragment {
  
  var latitudeKey = "latitude"
  
  var longitudeKey = "longitude"
  
}
