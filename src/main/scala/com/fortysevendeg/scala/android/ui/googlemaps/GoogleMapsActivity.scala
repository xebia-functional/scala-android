package com.fortysevendeg.scala.android.ui.googlemaps

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.ActionBarActivity
import android.view.{Menu, MenuItem}
import com.fortysevendeg.macroid.extras.ExtraFragment._
import com.fortysevendeg.scala.android.R
import macroid.Contexts
import googlemaps._

import scala.util.Random

class GoogleMapsActivity extends ActionBarActivity with Contexts[FragmentActivity] with Layout {
  
  val random = new Random()

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    toolBar map setSupportActionBar

    getSupportActionBar().setDisplayHomeAsUpEnabled(true)

  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    getMenuInflater.inflate(R.menu.activity_google_maps, menu)
    true
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case android.R.id.home => {
        finish()
        false
      }
      case R.id.add_marker => {
        findFragmentByTag[CustomMapFragment](fragmentTag) map (_.addMarker(
          getString(R.string.marker_map_sample_title),
          getString(R.string.marker_map_sample_message),
          initLatitude + random.nextDouble() / 100,
          initLongitude + random.nextDouble() / 100))
        true
      }
      case R.id.clear_map => {
        findFragmentByTag[CustomMapFragment](fragmentTag) map (_.clearMap())
        true
      }  
    }
    super.onOptionsItemSelected(item)
  }
}
