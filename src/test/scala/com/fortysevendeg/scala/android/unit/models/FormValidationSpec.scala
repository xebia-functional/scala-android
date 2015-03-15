package com.fortysevendeg.scala.android.unit.models

import com.fortysevendeg.scala.android.modules.validators.DummyFormValidation
import com.fortysevendeg.scala.android.ui.validateforms.FormModel
import com.fortysevendeg.scala.android.{BaseTestSupport, WithUnitTestsSupport}
import org.specs2.mutable.Specification

class FormValidationSpec
    extends Specification
    with BaseTestSupport
    with DummyFormValidation
    with WithUnitTestsSupport {

  "Name Validation in Dummy Form" should {

    "return an error when the name field is empty" in {

      val valueToTest = ""
      val editableField = mockEditText(valueToTest)

      val form = FormModel(Some(editableField), None, None)

      val result = isValidNameField(form)

      result.isSuccess === false
      result.toEither.left.get.details === s"name can not be empty."
    }

    "return success when the name field is specified" in {

      val valueToTest = "John Doe"
      val editableField = mockEditText(valueToTest)

      val form = FormModel(Some(editableField), None, None)

      val result = isValidNameField(form)

      result.isSuccess === true
    }
  }

  "Email Validation in Dummy Form" should {

    "return an error when the email field is empty" in {

      val valueToTest = ""
      val editableField = mockEditText(valueToTest)

      val form = FormModel(None, Some(editableField), None)

      val result = isValidEmailField(form)

      result.isSuccess === false
      result.toEither.left.get.details === s"email can not be empty."
    }

    "return success when an invalid email is specified" in {

      val valueToTest = "invalid.com"
      val editableField = mockEditText(valueToTest)

      val form = FormModel(None, Some(editableField), None)

      val result = isValidEmailField(form)

      result.isSuccess === false
      result.toEither.left.get.details === s"$valueToTest is not a valid email address."
    }

    "return success when a valid email is specified" in {

      val valueToTest = "johndoe@domain.com"
      val editableField = mockEditText(valueToTest)

      val form = FormModel(None, Some(editableField), None)

      val result = isValidEmailField(form)

      result.isSuccess === true
    }
  }

  "Age Validation in Dummy Form" should {

    "return an error when the age field is empty" in {

      val valueToTest = ""
      val editableField = mockEditText(valueToTest)

      val form = FormModel(None, None, Some(editableField))

      val result = isValidAgeField(form)

      result.isSuccess === false
      result.toEither.left.get.details === s"age can not be empty."
    }

    "return success when an invalid age is specified" in {

      val valueToTest = "AA"
      val editableField = mockEditText(valueToTest)

      val form = FormModel(None, None, Some(editableField))

      val result = isValidAgeField(form)

      result.isSuccess === false
      result.toEither.left.get.details === s"$valueToTest is an invalid number."
    }

    "return success when an invalid age (float number) is specified" in {

      val valueToTest = "4.4"
      val editableField = mockEditText(valueToTest)

      val form = FormModel(None, None, Some(editableField))

      val result = isValidAgeField(form)

      result.isSuccess === false
      result.toEither.left.get.details === s"$valueToTest is an invalid number."
    }

    "return success when a valid age is specified" in {

      val editableField = mockEditText("25")

      val form = FormModel(None, None, Some(editableField))

      val result = isValidAgeField(form)

      result.isSuccess === true
    }
  }
}