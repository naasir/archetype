package org.naasir.archetype

import cats.data.{ ValidatedNel }
import cats.data.Validated.{Invalid, Valid}
import cats.implicits._

/** An Email Address.
  *
  * Design Notes:
  * -------------
  * This is a value type with only one inner value: the email address.
  *
  * I considered storing the local-part of the email address and domain separately,
  * but felt that this was overkill as email addresses are almost always
  * specified as one single string.
  *
  * Finally, instances of this type can only be created via the provided smart constructor,
  * which ensures only valid instances can be created.
  *
  * On creation, the email address will be trimmed and converted to lowercase,
  * as hardly any email service or ISP enforces case sensitive email addresses.
  *
  * A valid EmailAddress is defined as follows:
  * 1. Matches the specified email address regex pattern
  * 2. Between 3 and 254 characters in length
  *
  * Inspiration:
  * -----------
  * https://github.com/hmrc/emailaddress
  */
class EmailAddress private[EmailAddress] (val value: String) extends AnyVal with TypedValue[String]

/** Companion object with factory methods for EmailAddress */
object EmailAddress {

  def apply(value: String): EmailAddress = this.create(Some(value)) match {
    case Valid(email) => email
    case Invalid(_) => throw new IllegalArgumentException(s"$value must be a valid email address")
  }

  def create(value: Option[String]): ValidatedNel[ValidationError, EmailAddress] = {
    (Validation.isDefined(value, "", "")
      |@| Validation.matches(value, "", "", RegexPatterns.email)
      |@| Validation.hasLengthBetween(value, "", "", 3, 254)
    ).map((_, _, _) => {
      new EmailAddress(value.getOrElse("").trim.toLowerCase)
    })
  }
}
