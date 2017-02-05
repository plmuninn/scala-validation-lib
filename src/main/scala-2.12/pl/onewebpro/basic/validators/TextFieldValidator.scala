package pl.onewebpro.basic.validators

import pl.onewebpro.validation.{Validation, Validator}
import pl.onewebpro.validation.validator.Validator

class TextFieldValidator(min: Int, max: Int) extends Validator[String] {
  override def apply(value: String): Validation[String] =
    if (value.length >= min && value.length <= max) Validator.success(value) else Validator.failure("error.wrong_length")
}
