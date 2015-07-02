package com.fortysevendeg.scala.android.modules.utils

import android.widget.EditText
import com.fortysevendeg.scala.android.R
import macroid.ContextWrapper
import com.fortysevendeg.macroid.extras.ResourcesExtras._

import scalaz._
import scalaz.std.list._
import scalaz.syntax.traverse._

object validation {

  type Validator[T] = (T) => Validation[ValidationMessage, T]

  case class ValidationMessage(
      widget: Option[EditText] = None,
      message: String,
      details: String = "")

  case class ValidationException(
      errorHead: ValidationMessage,
      errorTail: Seq[ValidationMessage] = Seq.empty)
      extends RuntimeException(s"${errorHead.message} - ${errorHead.details}")

  def WithValidation[T, ValT <: T, A, S]
  (validators: ((ValT) => Validation[ValidationMessage, T])*)
      (happyPath: => S)
      (implicit input: ValT, m: Manifest[T], contextWrapper: ContextWrapper): S = {

    validators.toList.map(p => p(input).toValidationNel).sequenceU match {
      case Failure(NonEmptyList(head: ValidationMessage)) => throw ValidationException(head)
      case Failure(list: NonEmptyList[_]) =>
        throw ValidationException(list.head.asInstanceOf[ValidationMessage], list.tail.asInstanceOf[List[ValidationMessage]])
      case Success(_) => happyPath
      case _ => throw ValidationException(ValidationMessage(message = resGetString(R.string.validate_error)))
    }
  }

}