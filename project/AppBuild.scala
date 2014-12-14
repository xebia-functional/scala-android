import sbt._

object AppBuild extends Build {

  import SettingsApp._

  lazy val root = Project(id = "root", base = file("."))
      .settings(rootSettings: _*)
      .aggregate(app, androidLib)

  lazy val app = Project(id = "app", base = file("modules/app"))
      .settings(appSettings: _*)
      .dependsOn(androidLib)

  val androidLib = Project(id = "androidLib",
    base = file("modules/androidLib"))
      .settings(androidLibSettings: _*)
}