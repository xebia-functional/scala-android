import sbt.Keys._
import sbt._

object SettingsDefault {

  lazy val settings =
    Seq(
      scalaVersion := Versions.scala,
      organization := "com.fortysevendeg",
      version := Versions.app,
      resolvers ++= Seq(
        Resolver.mavenLocal,
        DefaultMavenRepository,
        Resolver.typesafeRepo("releases"),
        Resolver.typesafeRepo("snapshots"),
        Resolver.typesafeIvyRepo("snapshots"),
        Resolver.sonatypeRepo("releases"),
        Resolver.sonatypeRepo("snapshots"),
        Resolver.defaultLocal,
        "jcenter" at "http://jcenter.bintray.com",
        "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
      )
    )
}
