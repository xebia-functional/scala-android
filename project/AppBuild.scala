import sbt._

object AppBuild extends Build {

  import SettingsApp._

  lazy val root = Project(id = "root", base = file("."))
      .settings(rootSettings: _*)
      .aggregate(app, macroidExtrasLib)

  lazy val app = Project(id = "app", base = file("modules/app"))
      .settings(appSettings: _*)
      .dependsOn(macroidExtrasLib)

  val macroidExtrasLib = Project(id = "macroidExtrasLib",
    base = file("modules/macroidExtrasLib"))
      .settings(macroidExtrasLibSettings: _*)
}