package com.fortysevendeg.scala.android.ui.apirequest

import android.location.Location
import android.os.Bundle
import android.support.v4.app.{Fragment, FragmentActivity}
import android.support.v7.app.ActionBarActivity
import com.fortysevendeg.scala.android.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.{LocationListener, LocationRequest, LocationServices}
import macroid.Contexts
import com.fortysevendeg.macroid.extras.FragmentExtras._
import LoaderFragment._
import ForecastFragment._
import macroid.FullDsl._

class ForecastApiRequestActivity 
  extends ActionBarActivity 
  with Contexts[FragmentActivity] 
  with Layout
  with GoogleApiClient.ConnectionCallbacks
  with GoogleApiClient.OnConnectionFailedListener
  with LocationListener {
  
  lazy val googleApiClient = new GoogleApiClient.Builder(this)
    .addConnectionCallbacks(this)
    .addOnConnectionFailedListener(this)
    .addApi(LocationServices.API)
    .build()

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    toolBar map setSupportActionBar

    getSupportActionBar.setDisplayHomeAsUpEnabled(true)
    
    googleApiClient.connect()
    
    if (savedInstanceState == null) {
      runUi(addFragment(f[LoaderFragment], Some(Id.fragment), Some(loaderFragmentName)))
    } 

  }
  
  def reconnectApiClient() = googleApiClient.reconnect()

  override def onConnected(bundle: Bundle) = {
    Option(LocationServices.FusedLocationApi.getLastLocation(googleApiClient)) match {
      case Some(lastLocation) => loadForecastFragment(lastLocation.getLatitude, lastLocation.getLongitude)
      case None => {
        val request = new LocationRequest
        request.setNumUpdates(1)
        request.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, request, this)
      }
    }
    
  }
  
  override def onConnectionSuspended(errorCode: Int) =
    showError(R.string.error_message_api_request_location_api)

  override def onConnectionFailed(connectionResult: ConnectionResult) =
    showError(R.string.error_message_api_request_location_api)
  
  override def onLocationChanged(location: Location) = {
    LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this)
    loadForecastFragment(location.getLatitude, location.getLongitude)
  }

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
