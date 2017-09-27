package org.naasir.archetype

import cats.data.{ ValidatedNel }
import cats.data.Validated.{Invalid, Valid}
import cats.implicits._

import java.nio.ByteBuffer
import org.apache.commons.codec.binary.Base64

/** A Universally-Unique Identifier (UUID).
  *
  * Design Notes:
  * -------------
  * This is a value type with only one inner value: the 22-character UUID string.
  * This UUID string is a Base 64, url-safe string.
  *
  * Inspiration:
  * -----------
  * http://stackoverflow.com/a/15013205
  */
class Uuid private[Uuid] (val value: String) extends AnyVal with TypedValue[String]

/** Companion object with factory methods */
object Uuid {

  def apply(value: String): Uuid = this.create(Some(value)) match {
    case Valid(uuid) => uuid
    case Invalid(_) => throw new IllegalArgumentException(s"$value must be a valid UUID")
  }

  def create(value: Option[String]): ValidatedNel[ValidationError, Uuid] = {
    (Validation.isDefined(value, "", "")
      |@| Validation.matches(value, "", "", RegexPatterns.base64UrlSafeString)
      |@| Validation.hasLengthExact(value, "", "", 22)
    ).map((_, _, _) => {
      new Uuid(value.getOrElse(""))
    })
  }

  def generate(): Uuid = {
    val uuid = java.util.UUID.randomUUID()
    val bb = ByteBuffer.wrap(Array.fill[Byte](16)(0))
    bb.putLong(uuid.getMostSignificantBits())
    bb.putLong(uuid.getLeastSignificantBits())
    new Uuid(Base64.encodeBase64URLSafeString(bb.array()))
  }
}
