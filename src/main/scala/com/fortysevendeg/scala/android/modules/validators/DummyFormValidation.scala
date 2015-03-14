package com.fortysevendeg.scala.android.modules.validators

import com.fortysevendeg.scala.android.modules.utils.validation.Validator
import com.fortysevendeg.scala.android.ui.validateforms.FormModel

trait DummyFormValidation extends BaseValidation {

  val validationDescriptionAction = "saving form"

  def isNameNonEmpty: Validator[FormModel] =
    input => hasRequiredField("name", input.name.getStringValue, validationDescriptionAction)(input)

  def isEmailNonEmpty: Validator[FormModel] =
    input => hasRequiredField("email", input.email.getStringValue, validationDescriptionAction)(input)

  def isAgeNonEmpty: Validator[FormModel] =
    input => hasRequiredField("age", input.age.getStringValue, validationDescriptionAction)(input)
}
