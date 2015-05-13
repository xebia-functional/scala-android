package com.fortysevendeg.scala.android.ui.googlemaps

import android.os.Bundle
import com.fortysevendeg.scala.android.R
import com.google.android.gms.maps.model.{MarkerOptions, LatLng}
import com.google.android.gms.maps.{CameraUpdateFactory, GoogleMap, OnMapReadyCallback, SupportMapFragment}
import googlemaps._

trait CustomMapFragment
  extends SupportMapFragment {
  
  def changeMapType(mapType: Int): Unit
  def moveCamera(latitude: Double, longitude: Double, zoom: Int): Unit
  def addMarker(title: String, snippet: String, latitude: Double, longitude: Double): Unit
  def clearMap(): Unit
}

class CustomMapFragmentImpl extends CustomMapFragment with OnMapReadyCallback {

  var googleMap: Option[GoogleMap] = None
  
  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    getMapAsync(this)
  }
  
  override def changeMapType(mapType: Int) = {
    googleMap map (_.setMapType(mapType))
  }
  
  override def moveCamera(latitude: Double, longitude: Double, zoom: Int = defaultZoom) = {
    googleMap map (_.moveCamera(
      CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoom)))
  }
  
  override def addMarker(title: String, snippet: String, latitude: Double, longitude: Double) = {
    googleMap map {
      val marker = new MarkerOptions()
        .title(title)
        .snippet(snippet)
        .position(new LatLng(latitude, longitude))
      _.addMarker(marker)      
    }    
  }

  override def clearMap() = {
    googleMap map (_.clear())
  }

  override def onMapReady(googleMap: GoogleMap) = {
    this.googleMap = Some(googleMap)
    googleMap.setMyLocationEnabled(true)
    moveCamera(initLatitude, initLongitude)
    addMarker(
      getString(R.string.marker_map_title),
      getString(R.string.marker_map_message),
      initLatitude,
      initLongitude
    )
  }
  
}
