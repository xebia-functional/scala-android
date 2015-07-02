package com.fortysevendeg.scala.android.modules.validators

import com.fortysevendeg.scala.android.modules.utils.validation.Validator
import com.fortysevendeg.scala.android.ui.validateforms.FormModel

import scalaz.Validation.FlatMap._

trait DummyFormValidation extends BaseValidation {

  val validationDescriptionAction = "saving form"

  val nameField = "name"

  val emailField = "email"

  val ageField = "age"

  def isValidNameField: Validator[FormModel] =
    input => hasRequiredField(input.name, nameField, input.name.getStringValue, validationDescriptionAction)(input)

  def isValidEmailField: Validator[FormModel] =
    input =>
      for {
        isFilled <- hasRequiredField(input.email, emailField, input.email.getStringValue, validationDescriptionAction)(input)
        isValidAtAll <- isValidEMail(input.email, input.email.getStringValue, validationDescriptionAction)(input)
      } yield isValidAtAll

  def isValidAgeField: Validator[FormModel] =
    input =>
      for {
        isFilled <- hasRequiredField(input.age, ageField, input.age.getStringValue, validationDescriptionAction)(input)
        isValidAtAll <- isValidNum(input.age, input.age.getStringValue, validationDescriptionAction)(input)
      } yield isValidAtAll
}