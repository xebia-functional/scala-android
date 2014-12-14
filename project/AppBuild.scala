import sbt._
import sbt.Keys._
import android.Keys._

object AppBuild extends Build {

  lazy val root = Project(id = "root", base = file("."))
      .settings(rootSettings: _*)
      .aggregate(app, androidLib)

  lazy val app = Project(id = "app", base = file("modules/app"))
      .settings(appSettings: _*)
      .dependsOn(androidLib)

  val androidLib = Project(id = "androidLib",
    base = file("modules/androidLib")).settings(
        android.Plugin.androidBuildApklib: _*)
      .settings(androidLibSettings: _*)

  lazy val rootSettings =
    Seq(
      scalaVersion := "2.11.4",
      platformTarget in Android := "android-21",
      install <<= install in(app, Android),
      run <<= run in(app, Android)
    )

  lazy val appSettings = android.Plugin.androidBuild(androidLib) ++
      List(
        scalaVersion := "2.11.4",
        transitiveAndroidLibs in Android := false,
        run <<= run in Android,
        apkbuildExcludes in Android ++= Seq(
          "META-INF/LICENSE.txt",
          "META-INF/NOTICE.txt"
        ),
        proguardScala in Android := true,
        useProguard in Android := true,
        proguardOptions in Android ++= Seq(
          "-ignorewarnings",
          "-keep class scala.Dynamic"
        )
      )

  lazy val androidLibSettings =
    List(
      scalaVersion := "2.11.4",
      exportJars in Test := false,
      resolvers ++= Seq(
        Resolver.sonatypeRepo("releases"),
        "jcenter" at "http://jcenter.bintray.com"
      ),
      libraryDependencies ++= Seq(
        aar("com.android.support" % "appcompat-v7" % "21.0.0"),
        aar("com.android.support" % "recyclerview-v7" % "21.0.0"),
        aar("com.android.support" % "cardview-v7" % "21.0.0"),
        aar("org.macroid" %% "macroid" % "2.0.0-M3"),
        aar("org.macroid" %% "macroid-akka-fragments" % "2.0.0-M3"),
        "com.typesafe.akka" %% "akka-actor" % "2.3.3",
        compilerPlugin("org.brianmckenna" %% "wartremover" % "0.10"))
    )
}