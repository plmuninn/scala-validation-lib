package pl.onewebpro.validation.core

import cats.data.NonEmptyList


package object error {

  trait ErrorValue {
    def message: String
  }

  case class SimpleError(message: String) extends ErrorValue

  case class ErrorWithKey(key: String, message: String) extends ErrorValue

  // Error when non empty list is empty
  lazy val nonEmptyListError = SimpleError("error.empty_list")

  // Error when someone tried to validate by empty multi validator
  lazy val multiValidatorError = SimpleError("error.empty_validator_list")

  // Error when extractor wasn't able to find required type
  lazy val invalidTypeError = SimpleError("error.invalid_type")

  // Error for empty string in basic NonEmptyStringValidator
  lazy val emptyStringError = SimpleError("error.empty_string")

  def composeError(key: String)(errors: NonEmptyList[ErrorValue]): NonEmptyList[ComposedError] =
    errors.map((error) => ComposedError(key, error))
}
