package pl.onewebpro.validation.basic.validator

import pl.onewebpro.validation.core.validator.Validator
import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.Validator
import pl.onewebpro.validation.core.entity.SimpleError


object NonEmptyStringFieldValidator extends Validator[String] {
  override def apply(value: String): Validation[String] =
    if (value.nonEmpty) {
      Validator.success(value)
    } else {
      Validator.failure(SimpleError("error.empty_string"))
    }
}
