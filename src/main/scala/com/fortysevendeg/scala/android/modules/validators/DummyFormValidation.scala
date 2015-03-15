package com.fortysevendeg.scala.android.modules.validators

import com.fortysevendeg.scala.android.modules.utils.validation.Validator
import com.fortysevendeg.scala.android.ui.validateforms.FormModel

import scalaz.Validation.FlatMap._

trait DummyFormValidation extends BaseValidation {

  val validationDescriptionAction = "saving form"

  def isValidNameField: Validator[FormModel] =
    input => hasRequiredField("name", input.name.getStringValue, validationDescriptionAction)(input)

  def isValidEmailField: Validator[FormModel] =
    input =>
      for {
        isFilled <- hasRequiredField("email", input.email.getStringValue, validationDescriptionAction)(input)
        isValidAtAll <- isValidEMail("email", input.email.getStringValue, validationDescriptionAction)(input)
      } yield isValidAtAll

  def isValidAgeField: Validator[FormModel] =
    input =>
      for {
        isFilled <- hasRequiredField("age", input.age.getStringValue, validationDescriptionAction)(input)
        isValidAtAll <- isValidNum("age", input.age.getStringValue, validationDescriptionAction)(input)
      } yield isValidAtAll
}