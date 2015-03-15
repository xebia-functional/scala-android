package com.fortysevendeg.scala.android

import android.text.Editable
import android.widget.EditText
import org.specs2.execute.{AsResult, Result}
import org.specs2.mock.Mockito
import org.specs2.specification.{Around, Scope}

trait BaseTestSupport extends Around with Scope {

  override def around[T: AsResult](t: => T): Result = AsResult.effectively(t)

}

trait WithUnitTestsSupport extends Mockito {

  def mockEditText(expectedValue: String) = {
    val editable = mock[Editable]
    val editableField = mock[EditText]
    editableField.getText returns editable
    editable.toString returns expectedValue
    editableField
  }
}