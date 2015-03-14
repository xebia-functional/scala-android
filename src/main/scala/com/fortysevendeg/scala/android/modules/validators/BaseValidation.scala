package com.fortysevendeg.scala.android.modules.validators

import android.widget.EditText
import com.fortysevendeg.scala.android.modules.utils.validation._

import scalaz.syntax.validation._

trait BaseValidation {

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
}