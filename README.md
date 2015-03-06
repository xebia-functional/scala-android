Scala API Demos [![Circle CI](https://circleci.com/gh/47deg/scala-android.svg?style=svg&circle-token=9e0f25c4e51c6eb2e3f637a2bedc88b67d905c13)](https://circleci.com/gh/47deg/scala-android)
=============

This repository contains different examples using Scala on Android. We use [Macroid](http://macroid.github.io/), a library for help us in GUI.

You can download the project from [Google Play](https://play.google.com/store/apps/details?id=com.fortysevendeg.scala.android)

Compile
======

You can compile this project and contribute improvements. To compile the project:

* Download [Activator](https://typesafe.com/community/core-tools/activator-and-sbt) and install it
* Configure the Android SDK on your computer
* Clone this GitHub project to your computer
* From project root directory run:

```
$ ./activator
```

* Connect your phone and execute:

```
> run
```

You can use your favorite IDE. At 47 Degrees we use IntelliJ with the Scala plugin. If you want to run this project from IntelliJ you only need to import the project.

Add Debug Keys
========

You need to add `debug.properties` file to your root project with the necessary keys for compile. The content should be:

```
openweather.api.key=***
google.map.key=***
```

Contribute your own examples
===============

If you want to learn *Scala on Android* and you want to share your examples, you can send us a PR with your new feature. 

You should make it of the next way:

* Create a new package inside `ui` for your sample
* The package for a feature contain all UI information (it's not necessary but you should consider it). This information is:
	* Activities
	* Fragments
	* Adapters
	* Styles: this file replace the XML Resources. All styles are in this file 
	* Layouts: this file replace the XML Resources. All layouts are in this file 
* Add your activity to `AndroidManifest.xml`
* Create a new item in `activities.json` inside `asset` directory. The JSON should be like:

```
  {
    "name": "Name of your example",
    "description": "Description of your example",
    "className": "Path of your activity",
    "minApi": "An integer designating the minimum API Level required ",
    "targetApi": "An integer designating the API Level that the application targets",
    "scalaLevel": "An integer designating the Scala Level of your example [1-3]",
    "androidLevel": "An integer designating the Android Level of your example [1-3]",
    "user" : {
      "avatar": "Your avatar URL",
      "name": "Your name",
      "twitter": "Your twitter username"
    }
  }
```
 
If you don't know what sample you can make, we help you. Here you have some examples:

* Transitions between the activities: you could use a new [Activity Transitions in Material Design](https://developer.android.com/training/material/animations.html#Transitions) like *Google Play Music*
* Validate forms with [Validation in ScalaZ](http://eed3si9n.com/learning-scalaz/Validation.html)


License
======

Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.


