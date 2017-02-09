package pl.onewebpro.validation.basic.validator

import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.Validator
import pl.onewebpro.validation.core.entity.SimpleError
import pl.onewebpro.validation.core.validator.Validator

class TextFieldValidator(min: Int, max: Int) extends Validator[String] {
  override def apply(value: String): Validation[String] =
    if (value.length >= min && value.length <= max) {
      Validator.success(value)
    } else {
      Validator.failure(SimpleError("error.wrong_length"))
    }
}
