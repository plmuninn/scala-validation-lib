package pl.onewebpro.validation

import pl.onewebpro.validation.entity.ValidationMap
import pl.onewebpro.validation.validator.Validator

object Implicits {
  implicit def pairToMap[T](pair: (String, Validator[T])): ValidationMap[T] = {
    val (key, validator) = pair
    ValidationMap(key, validator)
  }
}
