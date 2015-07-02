package com.fortysevendeg.scala.android.ui.validateforms

import android.widget._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.modules.utils.validation._
import com.fortysevendeg.scala.android.modules.validators.DummyFormValidation
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import com.fortysevendeg.macroid.extras.UIActionsExtras._
import macroid.FullDsl._
import macroid.{ContextWrapper, Tweak, ActivityContextWrapper, Ui}

import scala.util.{Failure, Success, Try}

trait Layout
    extends ToolbarLayout
    with Styles
    with DummyFormValidation {

  var name = slot[EditText]

  var age = slot[EditText]

  var email = slot[EditText]

  def layout(implicit activityContext: ActivityContextWrapper) = {
    getUi(
      l[LinearLayout](
        toolBarLayout <~ tbTitle(R.string.title_validate_form),
        l[ScrollView](
          l[LinearLayout](
            w[TextView] <~ textLabelStyle <~ text(R.string.validate_label_name),
            w[EditText] <~ editTextStyle <~ wire(name),
            w[TextView] <~ textLabelStyle <~ text(R.string.validate_label_email),
            w[EditText] <~ editTextStyle <~ wire(email),
            w[TextView] <~ textLabelStyle <~ text(R.string.validate_label_age),
            w[EditText] <~ editTextStyle <~ wire(age)
          ) <~ scrollContentStyle
        ) <~ scrollStyle,
        w[ImageView] <~ lineHorizontalStyle,
        w[Button] <~ saveButtonStyle <~ On.click {
          validationForm
        }
      ) <~ contentStyle
    )
  }

  def validationForm(implicit context: ContextWrapper): Ui[_] = {

    implicit val model = FormModel(name, email, age)

    Try(
      WithValidation(
        isValidNameField,
        isValidEmailField,
        isValidAgeField) {
        saveAction
      }) match {
      case Success(message) => message
      case Failure(ex: ValidationException) =>
        val uis = uiValidationMessage(ex.errorHead) +: (ex.errorTail map uiValidationMessage)
        Ui.sequence(uis :_*)
      case _ => defaultMessage
    }
  }

  def saveAction(implicit context: ContextWrapper): Ui[_] = uiShortToast(R.string.validate_successfully)

  def defaultMessage(implicit context: ContextWrapper): Ui[_] = uiShortToast(R.string.validate_error)

  def tvError(error: String) = Tweak[EditText](_.setError(error))

  private def uiValidationMessage(validationMessage: ValidationMessage)(implicit context: ContextWrapper): Ui[_] =
    validationMessage.widget <~ tvError(validationMessage.details)
}