package com.fortysevendeg.scala.android

import android.app.Application

import android.support.multidex.MultiDex

class ScalaAndroidApplication extends Application {
  
  override def onCreate(): Unit = {
    super.onCreate()
    MultiDex.install(this)
  }
}
