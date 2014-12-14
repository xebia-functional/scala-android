package com.fortysevendeg.scala.android.ui.textstyles

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.MenuItem
import com.fortysevendeg.scala.android.R
import macroid.Contexts

class TextStylesActivity extends ActionBarActivity with Contexts[ActionBarActivity] with Layout {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    //    DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    //    ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
    //      this,  mDrawerLayout, mToolbar,
    //      R.string.navigation_drawer_open, R.string.navigation_drawer_close
    //    );
    //    mDrawerLayout.setDrawerListener(mDrawerToggle);

    toolBar map setSupportActionBar

    getSupportActionBar().setDisplayHomeAsUpEnabled(true)

  }
  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case android.R.id.home => {
        finish()
        false
      }
    }
    super.onOptionsItemSelected(item)
  }
}
