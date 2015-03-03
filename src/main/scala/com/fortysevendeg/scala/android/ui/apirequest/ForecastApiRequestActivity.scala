package com.fortysevendeg.scala.android.ui.apirequest

import android.content.Context
import android.location.{LocationListener, Criteria, LocationManager, Location}
import android.os.Bundle
import android.support.v4.app.{Fragment, FragmentActivity}
import android.support.v7.app.ActionBarActivity
import macroid.Contexts
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.macroid.extras.FragmentExtras._
import LoaderFragment._
import ForecastFragment._
import macroid.FullDsl._

class ForecastApiRequestActivity 
  extends ActionBarActivity 
  with Contexts[FragmentActivity] 
  with Layout
  with LocationListener {

  lazy val locationManager = this.getSystemService(Context.LOCATION_SERVICE).asInstanceOf[LocationManager]

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    toolBar map setSupportActionBar

    getSupportActionBar.setDisplayHomeAsUpEnabled(true)

    if (savedInstanceState == null) {
      runUi(addFragment(f[LoaderFragment], Some(Id.fragment), Some(loaderFragmentName)))
    }

    reconnectApiClient
  }

  def reconnectApiClient = {
    val criteria = new Criteria()
    criteria.setAccuracy(Criteria.NO_REQUIREMENT)
    
    Option(locationManager.getBestProvider(criteria, true)) map { provider =>
      val last = Option(locationManager.getLastKnownLocation(provider))
      if (last.isDefined) loadForecastFragment(last.get.getLatitude, last.get.getLongitude)
      else locationManager.requestLocationUpdates(provider, 0, 0, this)
    }
  }

  override def onLocationChanged(location: Location): Unit = {
    locationManager.removeUpdates(this)
    loadForecastFragment(location.getLatitude, location.getLongitude)
  }

  override def onProviderEnabled(provider: String): Unit = {}

  override def onStatusChanged(provider: String, status: Int, extras: Bundle): Unit = {}

  override def onProviderDisabled(provider: String): Unit =
    showError(R.string.error_message_api_request_location_api)

  private def showError(errorMessage: Int) =
    findFragmentById[Fragment](Id.fragment) match {
      case Some(fragment) if fragment.isInstanceOf[LoaderFragment] => fragment.asInstanceOf[LoaderFragment].error(Some(errorMessage))
      case _ => runUi(replaceFragment(
        f[LoaderFragment].pass((showErrorKey, true), (errorMessageKey, errorMessage)),
        Id.fragment,
        Some(loaderFragmentName)))
  }
  
  private def loadForecastFragment(latitude: Double, longitude: Double) = {
    val bundle = new Bundle
    bundle.putDouble(latitudeKey, latitude)
    bundle.putDouble(longitudeKey, longitude)
    runUi(replaceFragment(
      f[ForecastFragment].pass(bundle),
      Id.fragment,
      Some(forecastFragmentName)))
  }
}
