package pl.onewebpro.basic.validators

import pl.onewebpro.validation.validator.Validator
import pl.onewebpro.validation.{Validation, Validator}


object NonEmptyStringFieldValidator extends Validator[String] {
  override def apply(value: String): Validation[String] =
    if (value.nonEmpty) Validator.success(value) else Validator.failure("error.empty_string")
}
