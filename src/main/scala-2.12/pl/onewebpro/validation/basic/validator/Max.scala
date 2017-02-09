package pl.onewebpro.validation.basic.validator

import pl.onewebpro.validation.core.entity.SimpleError
import pl.onewebpro.validation.core.validator.Validator
import pl.onewebpro.validation.core.{Validation, Validator}


class Max(max: Int) extends Validator[Int] {
  override def apply(value: Int): Validation[Int] =
    if (value > max) {
      Validator.failure(SimpleError("error.max_value"))
    } else {
      Validator.success(value)
    }
}
