package com.fortysevendeg.scala.android.modules.utils

import scalaz._
import scalaz.std.list._
import scalaz.syntax.traverse._

object validation {

  val defaultMessage = "Unexpected validation error."

  type Validator[T] = (T) => Validation[ValidationMessage, T]

  case class ValidationMessage(
      message: String,
      details: String = "")

  case class ValidationException(
      errorHead: ValidationMessage,
      errorTail: Seq[ValidationMessage] = Seq.empty)
      extends RuntimeException(s"${errorHead.message} - ${errorHead.details}")

  def WithValidation[T, ValT <: T, A, S]
  (validators: ((ValT) => Validation[ValidationMessage, T])*)
      (happyPath: => S)
      (implicit input: ValT, m: Manifest[T]): S = {

    validators.toList.map(p => p(input).toValidationNel).sequenceU match {
      case Failure(NonEmptyList(head: ValidationMessage)) => throw ValidationException(head)
      case Failure(list: NonEmptyList[_]) =>
        throw ValidationException(list.head.asInstanceOf[ValidationMessage], list.tail.asInstanceOf[List[ValidationMessage]])
      case Success(_) => happyPath
      case _ => throw ValidationException(ValidationMessage(defaultMessage))
    }
  }

  def defaultToStringErrors(ex: ValidationException) = {
    val sb = new StringBuilder()

    sb.append(ex.errorHead.message)
    sb.append("\n* ").append(ex.errorHead.details)
    ex.errorTail foreach( err => sb.append("\n* ").append(err.details))

    sb.toString()
  }
}