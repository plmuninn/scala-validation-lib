package pl.onewebpro.validation.core

import pl.onewebpro.validation.core.entity.ValidationMap
import pl.onewebpro.validation.core.validator.Validator

import scala.language.implicitConversions


object Implicits {
  implicit def pairToMap[T](pair: (String, Validator[T])): ValidationMap[T] = {
    val (key, validator) = pair
    ValidationMap(key, validator)
  }
}
