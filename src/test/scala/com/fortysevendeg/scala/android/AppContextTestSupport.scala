package com.fortysevendeg.scala.android

import com.fortysevendeg.macroid.extras.AppContextProvider
import macroid.AppContext
import org.specs2.mock.Mockito
import org.specs2.specification.Scope

trait AppContextTestSupport
    extends Mockito
    with AppContextProvider
    with TestConfig
    with Scope {

  implicit val appContextProvider: AppContext = mock[AppContext]

  appContextProvider.get returns mockContext

}
