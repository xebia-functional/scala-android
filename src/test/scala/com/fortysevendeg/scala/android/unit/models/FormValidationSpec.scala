package com.fortysevendeg.scala.android.unit.models

import android.text.Editable
import android.widget.EditText
import com.fortysevendeg.scala.android.BaseTestSupport
import com.fortysevendeg.scala.android.modules.validators.DummyFormValidation
import com.fortysevendeg.scala.android.ui.validateforms.FormModel
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class FormValidationSpec
    extends Specification
    with BaseTestSupport
    with Mockito
    with DummyFormValidation {

  "Form Validation" should {

    "return an error when the name field is empty" in {

      val editable = mock[Editable]
      val nameField = mock[EditText]
      nameField.getText returns editable
      editable.toString returns ""

      val form = FormModel(Some(nameField), None, None)

      val result = isValidNameField(form)

      result.isSuccess === false
      result.toEither.left.get.details === "name can not be empty."
    }
  }
}