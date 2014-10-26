Scala API Demos
=============

This repository contains different examples using Scala on Android. We use [Macroid](http://macroid.github.io/), a library for help us in GUI.

Project Structure
=============

Currently we have 2 packages:

* "macroid": we add extension to macroid library. ExtraTweaks contains Tweaks separate by Widget that we can use in our layouts
* "ui": we have all features for our project. Previously we created packages by types (activities, fragments, adapters, etc...) if we separate by features, we can move easier some feature to other project only copy/paste de package. Also this package contain "commons" and 
"components". These packages contain commons elements for UI

Features packages
===============

The package for a feature contain all UI information. This information is:

* Activities
* Fragments
* Adapters
* Styles: this file replace the XML Resources. All styles are in this file 
* Layouts: this file replace the XML Resources. All layouts are in this file 

Demos
=====

* Main: this feature is the main feature and contain a list where we access to all examples. We are using the new views CardView and RecyclerView (replace old ListView and GridView in Lollipop). These views are loaded from AppCompat-v7 and we can load it in older Android Versions
* Ripple Background: this feature is only for Lollipop because we are using the new Ripple Animations that they aren't in AppCompat library
* Text Styles: it's a simple example where we can see how load different styles in TextViews