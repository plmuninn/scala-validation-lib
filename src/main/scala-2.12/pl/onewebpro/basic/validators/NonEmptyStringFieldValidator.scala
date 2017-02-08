package pl.onewebpro.basic.validators

import pl.onewebpro.validation.core.validator.Validator
import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.Validator


object NonEmptyStringFieldValidator extends Validator[String] {
  override def apply(value: String): Validation[String] =
    if (value.nonEmpty) Validator.success(value) else Validator.failure("error.empty_string")
}
