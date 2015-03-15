package com.fortysevendeg.scala.android.modules.validators

import android.widget.EditText
import com.fortysevendeg.scala.android.modules.utils.validation._

import scalaz.syntax.validation._

trait BaseValidation {

  private val emailRegex =
    """
      |^(?!\.)("([^"\r\\]|\\["\r\\])*"|([-a-zA-Z0-9!#$%&'*+/=?^_`{|}~]|(?<!\.)\.)*)
      |(?<!\.)@[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]\.[a-zA-Z][a-zA-Z\.]*[a-zA-Z]$
      | """.r

  private val numericRegex = """^[1-9][0-9]?[0-9]?$""".r

  implicit class RichEditText(editText: Option[EditText]) {
    def getStringValue: String = editText map { text =>
      text.getText.toString
    } getOrElse ""
  }

  def hasRequiredField[T](
      name: String,
      value: String,
      action: String): Validator[T] = input =>
    value.isEmpty match {
      case true => ValidationMessage(s"Error $action.", s"$name can not be empty.").failure
      case _ => input.success
    }

  def isValidEMail[T](
      name: String,
      value: String,
      action: String): Validator[T] = input =>
    emailRegex.findFirstMatchIn(value)
        .map(_ => input.success)
        .getOrElse(ValidationMessage(s"Error $action.", s"$name is not a valid email address.").failure)

  def isValidNum[T](
      name: String,
      value: String,
      action: String): Validator[T] = input =>
    numericRegex.findFirstMatchIn(value)
        .map(_ => input.success)
        .getOrElse(ValidationMessage(s"Error $action.", s"$name is an invalid number.").failure)
}