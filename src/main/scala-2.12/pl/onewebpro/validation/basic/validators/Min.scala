package pl.onewebpro.validation.basic.validators

import pl.onewebpro.validation.core.entity.SimpleError
import pl.onewebpro.validation.core.{Validation, Validator}
import pl.onewebpro.validation.core.validator.Validator


class Min(min: Int) extends Validator[Int] {
  override def apply(value: Int): Validation[Int] =
    if (value < min) {
      Validator.failure(SimpleError("error.min_value"))
    } else {
      Validator.success(value)
    }
}
