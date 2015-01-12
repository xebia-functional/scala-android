import Libraries.android._
import Libraries.macroid._
import Libraries.akka._
import Libraries.playServices._

android.Plugin.androidBuild

platformTarget in Android := Versions.androidPlatformV

name := "scala-android"

organization := "com.fortysevendeg"

organizationName := "47 Degrees"

organizationHomepage := Some(new URL("http:47deg.com"))

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
  akkaActor,
  aar(Libraries.androidMultidex),
  compilerPlugin(Libraries.wartRemover))

run <<= run in Android

proguardOptions in Android ++= Settings.proguardCommons ++ Settings.proguardAkka

proguardScala in Android := true

useProguard in Android := true

useProguardInDebug in Android := true

dexMulti in Android := true

dexMaxHeap in Android := "2g"

dexMinimizeMainFile in Android := true

dexMainFileClasses in Android := Seq(
  "com/example/app/MultidexApplication.class",
  "android/support/multidex/BuildConfig.class",
  "android/support/multidex/MultiDex$V14.class",
  "android/support/multidex/MultiDex$V19.class",
  "android/support/multidex/MultiDex$V4.class",
  "android/support/multidex/MultiDex.class",
  "android/support/multidex/MultiDexApplication.class",
  "android/support/multidex/MultiDexExtractor$1.class",
  "android/support/multidex/MultiDexExtractor.class",
  "android/support/multidex/ZipUtil$CentralDirectory.class",
  "android/support/multidex/ZipUtil.class"
)


apkbuildExcludes in Android ++= Seq(
  "META-INF/MANIFEST.MF",
  "META-INF/LICENSE.txt",
  "META-INF/LICENSE",
  "META-INF/NOTICE.txt",
  "META-INF/NOTICE"
)
