package pl.onewebpro.validation.basic.validator

import pl.onewebpro.validation.core.entity.WrongLengthError
import pl.onewebpro.validation.core.validator.Validator
import pl.onewebpro.validation.core.{Validation, Validator}


class Max(max: Int) extends Validator[Int] {
  override def apply(value: Int): Validation[Int] =
    if (value > max) {
      Validator.failure {
        WrongLengthError("error.max_value", value, None, Some(max))
      }
    } else {
      Validator.success(value)
    }
}
