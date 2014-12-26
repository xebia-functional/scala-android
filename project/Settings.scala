import sbt._

object Settings {

  lazy val resolvers =
    Seq(
        Resolver.mavenLocal,
        DefaultMavenRepository,
        Resolver.typesafeRepo("releases"),
        Resolver.typesafeRepo("snapshots"),
        Resolver.typesafeIvyRepo("snapshots"),
        Resolver.sonatypeRepo("releases"),
        Resolver.sonatypeRepo("snapshots"),
        Resolver.defaultLocal,
        "jcenter" at "http://jcenter.bintray.com",
        "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
        "47deg Public" at "http://clinker.47deg.com/nexus/content/groups/public",
        "47deg Private Snapshot Repository" at "http://clinker.47deg.com/nexus/content/repositories/private-snapshots",
        "47deg Private Release Repository" at "http://clinker.47deg.com/nexus/content/repositories/private-releases"
      )

  lazy val proguardCommons = Seq(
    "-ignorewarnings",
    "-keep class scala.Dynamic")

  lazy val proguardAkka = Seq(
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
