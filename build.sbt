import Libraries.android._
import Libraries.macroid._
import Libraries.akka._
import Libraries.playServices._
import Libraries.utils._

android.Plugin.androidBuild

platformTarget in Android := Versions.androidPlatformV

name := "scala-android"

organization := "com.fortysevendeg"

organizationName := "47 Degrees"

organizationHomepage := Some(new URL("http://47deg.com"))

version := Versions.appV

scalaVersion := Versions.scalaV

scalacOptions ++= Seq("-feature", "-deprecation")

credentials += Credentials(new File(Path.userHome.absolutePath + "/.ivy2/.credentials"))

resolvers ++= Settings.resolvers

libraryDependencies ++= Seq(
  aar(macroidRoot),
  aar(macroidAkkaFragments),
  aar(androidAppCompat),
  aar(androidCardView),
  aar(androidRecyclerview),
  aar(macroidExtras),
  aar(playServicesMaps),
  sprayJson,
  akkaActor,
  compilerPlugin(Libraries.wartRemover))

run <<= run in Android

proguardScala in Android := true

useProguard in Android := true

proguardOptions in Android ++= Settings.proguardCommons ++ Settings.proguardAkka