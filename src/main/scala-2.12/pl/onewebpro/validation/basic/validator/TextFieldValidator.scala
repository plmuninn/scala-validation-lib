package pl.onewebpro.validation.basic.validator

import pl.onewebpro.validation.core.{Validation, Validator}
import pl.onewebpro.validation.core.entity.WrongLengthError
import pl.onewebpro.validation.core.validator.Validator

class TextFieldValidator(min: Int, max: Int) extends Validator[String] {
  override def apply(value: String): Validation[String] =
    if (value.length >= min && value.length <= max) {
      Validator.success(value)
    } else {
      Validator.failure {
        WrongLengthError("error.wrong_length", value.length, Some(min), Some(min))
      }
    }
}
