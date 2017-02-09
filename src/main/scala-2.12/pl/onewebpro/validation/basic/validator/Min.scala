package pl.onewebpro.validation.basic.validator

import pl.onewebpro.validation.core.entity.WrongLengthError
import pl.onewebpro.validation.core.validator.Validator
import pl.onewebpro.validation.core.{Validation, Validator}


class Min(min: Int) extends Validator[Int] {
  override def apply(value: Int): Validation[Int] =
    if (value < min) {
      Validator.failure {
        WrongLengthError("error.min_value", value, Some(min), None)
      }
    } else {
      Validator.success(value)
    }
}
