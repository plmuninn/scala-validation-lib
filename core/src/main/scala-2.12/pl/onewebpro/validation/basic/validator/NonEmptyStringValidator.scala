package pl.onewebpro.validation.basic.validator

import pl.onewebpro.validation.core.{Validation, Validator}
import pl.onewebpro.validation.core.error._
import pl.onewebpro.validation.core.validator.Validator


object NonEmptyStringValidator extends Validator[String] {
  override def apply(value: String): Validation[String] =
    if (value.nonEmpty) {
      Validator.success(value)
    } else {
      Validator.failure(emptyStringError)
    }
}
