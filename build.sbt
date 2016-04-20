import Libraries.android._
import Libraries.macroid._
import Libraries.akka._
import Libraries.playServices._
import Libraries.graphics._
import Libraries.json._
import Libraries.net._
import Libraries.test._
import ReplacePropertiesGenerator._
import android.PromptPasswordsSigningConfig

android.Plugin.androidBuild

platformTarget in Android := Versions.androidPlatformV

name := "scala-android"

organization := "com.fortysevendeg"

organizationName := "47 Degrees"

organizationHomepage := Some(new URL("http://47deg.com"))

version := Versions.appV

scalaVersion := Versions.scalaV

scalacOptions ++= Seq("-feature", "-deprecation")

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.7")

resolvers ++= Settings.resolvers

libraryDependencies ++= Seq(
  aar(macroidRoot),
  aar(macroidAkkaFragments),
  aar(androidDesign),
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
  androidTest)

transitiveAndroidLibs in Android := true

run <<= run in Android

apkSigningConfig in Android := Option(
  PromptPasswordsSigningConfig(
    keystore = new File(Path.userHome.absolutePath + "/.android/signed.keystore"),
    alias = "47deg"))

proguardScala in Android := true

useProguard in Android := true

useProguardInDebug in Android := true

proguardOptions in Android ++= Settings.proguardCommons ++ Settings.proguardAkka

packagingOptions in Android := PackagingOptions(
  Seq("META-INF/LICENSE",
    "META-INF/LICENSE.txt",
    "META-INF/NOTICE",
    "META-INF/NOTICE.txt"))

packageRelease <<= (packageRelease in Android).dependsOn(setDebugTask(false))
packageResources in Android <<= (packageResources in Android).dependsOn(replaceValuesTask)
