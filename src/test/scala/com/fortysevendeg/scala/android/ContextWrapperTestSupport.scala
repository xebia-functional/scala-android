package com.fortysevendeg.scala.android

import macroid.ContextWrapper
import org.specs2.mock.Mockito
import org.specs2.specification.Scope

trait ContextWrapperTestSupport
    extends Mockito
    with TestConfig
    with Scope {

  implicit val context: ContextWrapper = mock[ContextWrapper]

  context.application returns mockContext

}
