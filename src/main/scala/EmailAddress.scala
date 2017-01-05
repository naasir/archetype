package org.naasir.archetype

import cats.data.{ ValidatedNel }
import cats.data.Validated.{Invalid, Valid}
import cats.implicits._

/** An Email Address.
  *
  * Design Notes:
  * -------------
  * This is a pretty primitive type with only one field: the address.
  * I considered storing the local-part of the email address and domain separately,
  * but felt that this was overkill as email addresses are almost always
  * specified as one single string.
  *
  * Finally, instances of this type can only be created via the provided smart constructor,
  * which ensures only valid instances can be created.
  *
  * A valid EmailAddress is defined as follows:
  * 1. Matches the specified email address regex pattern
  * 2. Between 3 and 254 characters in length
  *
  * References:
  * -----------
  */
abstract case class EmailAddress private[EmailAddress] (address: String) {
  private def readResolve(): Object = EmailAddress(address)
  def copy(): EmailAddress = EmailAddress(address)
}

/** Companion object with factory methods for EmailAddress */
object EmailAddress {

  def apply(input: String): EmailAddress = this.create(Some(input)) match {
    case Valid(email) => email
    case Invalid(_) => throw new IllegalArgumentException(s"$input must be a valid email address")
  }

  def create(input: Option[String]): ValidatedNel[ValidationError, EmailAddress] = {
    (Validation.isDefined(input, "", "")
      |@| Validation.isEmail(input, "", "")
      |@| Validation.hasLengthBetween(input, "", "", 3, 254)
    ).map((_, _, _) => {
      new EmailAddress(input.getOrElse("").trim.toLowerCase) {}
    })
  }
}
