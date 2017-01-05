package org.naasir.archetype

/** Enumeration of validation error codes. */
object ValidationErrorCode extends Enumeration {
  type ValidationErrorCode = Value

  val AlreadyExists = Value("already_exists")
  val InsufficientPermissions = Value("insufficient_permissions")
  val InvalidLength = Value("invalid_length")
  val InvalidFormat = Value("invalid_format")
  val InvalidRange = Value("invalid_range")
  val InvalidRequest = Value("invalid_request")
  val MissingField = Value("missing_field")
  val NotFound = Value("not_found")
}

/** A validation error
  *
  * @param code the validation error code
  * @param field the field (property) that failed validation
  * @param resource the resource (object) that the field belongs to
  * @param message a custom error message
  */
case class ValidationError(
  code: String,
  field: String,
  resource: String,
  message: Option[String] = None
) {

  /** Creates a copy of this object with a new message
    *
    * @param message the new message
    * @return a copy with the new message set
    */
  def withMessage(message: String) = this.copy(message = Some(message))
}

/** Companion object with different ValidationError constructors */
object ValidationErrors {
  // @todo convert these to case classes?
  def AlreadyExists(field: String, resource: String) = ValidationError(ValidationErrorCode.AlreadyExists.toString, field, resource)

  def InsufficientPermissions = ValidationError(ValidationErrorCode.InsufficientPermissions.toString, "", "")
    .withMessage("The user associated with the access token provided, does not have sufficient permissions to make this request.")

  def InvalidLength(field: String, resource: String) = ValidationError(ValidationErrorCode.InvalidLength.toString, field, resource)

  def InvalidFormat(field: String, resource: String) = ValidationError(ValidationErrorCode.InvalidFormat.toString, field, resource)

  def InvalidRange(field: String, resource: String) = ValidationError(ValidationErrorCode.InvalidRange.toString, field, resource)

  def InvalidRequest(field: String, resource: String) = ValidationError(ValidationErrorCode.InvalidRequest.toString, field, resource)

  def MissingField(field: String, resource: String) = ValidationError(ValidationErrorCode.MissingField.toString, field, resource)

  def NotFound(field: String, resource: String) = ValidationError(ValidationErrorCode.NotFound.toString, field, resource)
}
