import Libraries.akka._
import Libraries.android._
import Libraries.macroid._
import android.Keys._
import sbt.Keys._
import sbt._

object SettingsApp {

  import AppBuild._

  lazy val rootSettings =
    SettingsDefault.settings ++
        Seq(
          platformTarget in Android := Versions.androidPlatformV,
          install <<= install in(app, Android),
          run <<= run in(app, Android)
        )

  lazy val appSettings =
    android.Plugin.androidBuild(macroidExtrasLib) ++
        SettingsDefault.settings ++
        SettingsProguard.settings ++
        Seq(
          transitiveAndroidLibs in Android := false,
          run <<= run in Android,
          apkbuildExcludes in Android ++= Seq(
            "META-INF/LICENSE.txt",
            "META-INF/NOTICE.txt"
          )
        )

  lazy val macroidExtrasLibSettings =
    android.Plugin.androidBuildApklib ++
    SettingsDefault.settings ++
        Seq(
          exportJars in Test := false,
          libraryDependencies ++= Seq(
            aar(macroidRoot),
            aar(macroidAkkaFragments),
            aar(androidAppCompat),
            aar(androidCardView),
            aar(androidRecyclerview),
            akkaActor,
            compilerPlugin(Libraries.wartRemover))
        )
}
