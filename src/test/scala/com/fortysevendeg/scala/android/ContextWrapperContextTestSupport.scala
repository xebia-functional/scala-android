package com.fortysevendeg.scala.android

import android.content.Context
import macroid.ContextWrapper
import org.specs2.mock.Mockito

trait ContextWrapperContextTestSupport
    extends Mockito {

  val mockContext = mock[Context]

  implicit val context: ContextWrapper = mock[ContextWrapper]

  context.application returns mockContext

}
