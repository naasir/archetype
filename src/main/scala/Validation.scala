package org.naasir.archetype

import scala.util.matching.Regex
import cats.data.Validated.{ invalidNel, valid }
import cats.data.ValidatedNel

import ValidationErrors._

object Validation {

  /** Checks that the specified value is defined
    *
    * @param valueOpt the potential value to validate
    * @param field the name of the field on the resource
    * @param resource the name of the resource
    * @return Either the value or a collection of validation errors
    */
  def isDefined[T](valueOpt: Option[T], field: String, resource: String): ValidatedNel[ValidationError, Option[T]] = valueOpt match {
    case None => invalidNel(MissingField(field, resource))
    case Some(_) => valid(valueOpt)
  }

  /** Checks that the specified value collection is defined
    *
    * @param valueCollection the potential colleection to validate
    * @param field the name of the field on the resource
    * @param resource the name of the resource
    * @return Either the value or a collection of validation errors
    */
  def isDefined[T](valueCollection: collection.Iterable[T], field: String, resource: String): ValidatedNel[ValidationError, collection.Iterable[T]] = valueCollection match {
    case Nil => invalidNel(MissingField(field, resource))
    case _ => valid(valueCollection)
  }

  /** Checks the specified string to see if it is a valid email address
    *
    * @param valueOpt the potential value to validate
    * @param field the name of the field on the resource
    * @param resource the name of the resource
    * @return Either the value or a collection of validation errors
    */
  def isEmail(valueOpt: Option[String], field: String, resource: String): ValidatedNel[ValidationError, Option[String]] = {
    this.isPattern(valueOpt, field, resource, RegexPatterns.email)
  }

  /** Checks the specified string to see if it is a valid email address
    *
    * @param valueOpt the potential value to validate
    * @param field the name of the field on the resource
    * @param resource the name of the resource
    * @return Either the value or a collection of validation errors
    */
  def isPattern(valueOpt: Option[String], field: String, resource: String, pattern: Regex): ValidatedNel[ValidationError, Option[String]] = valueOpt match {
    case None => valid(valueOpt)
    case Some(input) => RegexPatterns.email.findFirstIn(input) match {
      case None => invalidNel(InvalidFormat(field, resource))
      case Some(_) => valid(valueOpt)
    }
  }

  /** Checks the max length of a specified string
    *
    * @param valueOpt the potential value to validate
    * @param field the name of the field on the resource
    * @param resource the name of the resource
    * @param max the maximum allowed length for the field
    * @return Either the value or a collection of validation errors
    */
  def hasLengthMax(valueOpt: Option[String], field: String, resource: String, max: Int): ValidatedNel[ValidationError, Option[String]] = {
    val length = valueOpt.getOrElse("").trim.length

    if (valueOpt.isDefined && length > max) {
      invalidNel(InvalidLength(field, resource).withMessage(s"${field} should be at most ${max} characters."))
    }
    else {
      valid(valueOpt)
    }
  }

  /** Checks the length of a specified string
    *
    * @param valueOpt the potential value to validate
    * @param field the name of the field on the resource
    * @param resource the name of the resource
    * @param min the minimum allowed length for the field
    * @param max the maximum allowed length for the field
    * @return Either the value or a collection of validation errors
    */
  def hasLengthBetween(valueOpt: Option[String], field: String, resource: String, min: Int, max: Int): ValidatedNel[ValidationError, Option[String]] = {
    val length = valueOpt.getOrElse("").trim.length

    if (valueOpt.isDefined && (length < min || length > max)) {
      invalidNel(InvalidLength(field, resource).withMessage(s"${field} should be between ${min} and ${max} characters."))
    }
    else {
      valid(valueOpt)
    }
  }
}
