import android.Keys._
import sbt.Keys._
import sbt._

object SettingsProguard {

  lazy val settings = Seq(
    proguardScala in Android := true,
    useProguard in Android := true,
    proguardCommonConfig,
    proguardAkkaConfig
  )

  lazy val proguardCommonConfig = proguardOptions in Android ++= Seq(
    "-ignorewarnings",
    "-keep class scala.Dynamic")

  lazy val proguardAkkaConfig = proguardOptions in Android ++= Seq(
    "-keep class akka.actor.Actor$class { *; }",
    "-keep class akka.actor.LightArrayRevolverScheduler { *; }",
    "-keep class akka.actor.LocalActorRefProvider { *; }",
    "-keep class akka.actor.CreatorFunctionConsumer { *; }",
    "-keep class akka.actor.TypedCreatorFunctionConsumer { *; }",
    "-keep class akka.dispatch.BoundedDequeBasedMessageQueueSemantics { *; }",
    "-keep class akka.dispatch.UnboundedMessageQueueSemantics { *; }",
    "-keep class akka.dispatch.UnboundedDequeBasedMessageQueueSemantics { *; }",
    "-keep class akka.dispatch.DequeBasedMessageQueueSemantics { *; }",
    "-keep class akka.dispatch.MultipleConsumerSemantics { *; }",
    "-keep class akka.actor.LocalActorRefProvider$Guardian { *; }",
    "-keep class akka.actor.LocalActorRefProvider$SystemGuardian { *; }",
    "-keep class akka.dispatch.UnboundedMailbox { *; }",
    "-keep class akka.actor.DefaultSupervisorStrategy { *; }",
    "-keep class macroid.akkafragments.AkkaAndroidLogger { *; }",
    "-keep class akka.event.Logging$LogExt { *; }")
}
