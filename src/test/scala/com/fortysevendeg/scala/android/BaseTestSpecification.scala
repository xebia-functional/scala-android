package com.fortysevendeg.scala.android

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

trait BaseTestSpecification extends Specification {

  def await[T](future: Future[T]) = Await.result(future, Duration.Inf)

  trait BaseTestScope extends Scope

}
