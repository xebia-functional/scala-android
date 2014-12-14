import sbt._

object Libraries {

  def onCompile(dep: ModuleID): ModuleID = dep % "compile"
  def onTest(dep: ModuleID): ModuleID = dep % "test"

  object compilePlugin {

    lazy val wartRemover = "org.brianmckenna" %% "wartremover" % Versions.wartremover
  }

  object scala {

    lazy val scalaReflect = "org.scala-lang" % "scala-reflect" % Versions.scala
    lazy val scalap = "org.scala-lang" % "scalap" % Versions.scala
  }

  object android {

    def androidDep(module: String) = "com.android.support" % module % Versions.androidV

    lazy val androidSupportv4 = androidDep("support-v4")
    lazy val androidAppCompat = androidDep("appcompat-v7")
    lazy val androidRecyclerview = androidDep("recyclerview-v7")
    lazy val androidCardView = androidDep("cardview-v7")
  }

  object akka {

    def akka(module: String) = "com.typesafe.akka" %% s"akka-$module" % Versions.akka

    lazy val akkaActor = akka("actor")
    lazy val akkaTestKit = akka("testkit")

  }

  object macroid {

    def macroid(module: String = "") =
      "org.macroid" %% s"macroid${if(!module.isEmpty) s"-$module" else ""}" % Versions.macroid

    lazy val macroidRoot = macroid()
    lazy val macroidAkkaFragments = macroid("akka-fragments")
  }
}