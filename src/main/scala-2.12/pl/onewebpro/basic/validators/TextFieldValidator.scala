package pl.onewebpro.basic.validators

import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.Validator
import pl.onewebpro.validation.core.validator.Validator

class TextFieldValidator(min: Int, max: Int) extends Validator[String] {
  override def apply(value: String): Validation[String] =
    if (value.length >= min && value.length <= max) Validator.success(value) else Validator.failure("error.wrong_length")
}
