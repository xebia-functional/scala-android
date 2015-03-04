import Libraries.android._
import Libraries.macroid._
import Libraries.akka._
import Libraries.playServices._
import Libraries.graphics._
import Libraries.json._
import Libraries.net._
import Libraries.test._
import ReplacePropertiesGenerator._

android.Plugin.androidBuild

platformTarget in Android := Versions.androidPlatformV

name := "scala-android"

organization := "com.fortysevendeg"

organizationName := "47 Degrees"

organizationHomepage := Some(new URL("http://47deg.com"))

version := Versions.appV

scalaVersion := Versions.scalaV

scalacOptions ++= Seq("-feature", "-deprecation")

resolvers ++= Settings.resolvers

libraryDependencies ++= Seq(
  aar(macroidRoot),
  aar(macroidAkkaFragments),
  aar(androidAppCompat),
  aar(androidCardView),
  aar(androidRecyclerview),
  aar(macroidExtras),
  aar(playServicesMaps),
  playJson,
  picasso,
  communicator,
  akkaActor,
  specs2,
  mockito,
  androidTest,
  compilerPlugin(Libraries.wartRemover))

packageRelease <<= (packageRelease in Android).dependsOn(setDebugTask(false))

run <<= run in Android

proguardScala in Android := true

useProguard in Android := true

proguardOptions in Android ++= Settings.proguardCommons ++ Settings.proguardAkka

apkbuildExcludes in Android ++= Seq("META-INF/LICENSE.txt", "META-INF/NOTICE.txt", "META-INF/LICENSE", "META-INF/NOTICE")

packageResources in Android <<= (packageResources in Android).dependsOn(replaceValuesTask)