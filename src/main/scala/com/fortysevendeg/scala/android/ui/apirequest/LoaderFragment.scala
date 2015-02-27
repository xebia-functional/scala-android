package com.fortysevendeg.scala.android.ui.apirequest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{View, ViewGroup, LayoutInflater}
import com.fortysevendeg.scala.android.R
import macroid.{Ui, Contexts}
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._

class LoaderFragment extends Fragment with Contexts[Fragment] {

  private var fragmentLayout: Option[LoaderFragmentLayout] = None

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {

    val fLayout = new LoaderFragmentLayout

    fragmentLayout = Some(fLayout)

    fLayout.reloadButton <~ On.click(Ui {
      reload
    })

    fLayout.layout

  }

  override def onViewCreated(view: View, savedInstanceState: Bundle): Unit = {
    super.onViewCreated(view, savedInstanceState)
    
    import LoaderFragment._
    
    Option(getArguments) map { bundle =>
      if (bundle.getBoolean(showErrorKey, false)) error(Option(bundle.getInt(errorMessageKey)))
    }
  }

  def reload =
    Option(getActivity) map (_.asInstanceOf[ForecastApiRequestActivity].reconnectApiClient())
  
  def loading =
    fragmentLayout map { layout =>
      runUi(
        (layout.progressBar <~ vVisible) ~
        (layout.errorContent <~ vGone)
      )
    }
  
  def error(errorMessage: Option[Int]) =
    fragmentLayout map { layout =>
      runUi(
        (layout.progressBar <~ vGone) ~
          (layout.errorContent <~ vVisible) ~
            (layout.errorText <~ tvText(errorMessage getOrElse R.string.error_message_api_request_default))
      )
    }

}

object LoaderFragment {
  
  val showErrorKey = "showError"
  
  val errorMessageKey = "errorMessage"
  
}
