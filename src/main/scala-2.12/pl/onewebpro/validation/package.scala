package pl.onewebpro

import pl.onewebpro.validation.basic.validators.{NonEmptyStringFieldValidator, TextFieldValidator}
import pl.onewebpro.validation.core.entity.ValidationMap
import pl.onewebpro.validation.core.schema.Schema
import pl.onewebpro.validation.core.validator.{CollectionV, OptionalV, OptionalValidator, Validator}

import scala.language.implicitConversions

package object validation {

  lazy val schema = Schema

  lazy val nonEmptyString = NonEmptyStringFieldValidator

  lazy val textValidator: TextFieldValidator = textValidator()

  def textValidator(min: Int = 0, max: Int = 0): TextFieldValidator = new TextFieldValidator(min, max)

  def optional[T](validator: Validator[T]): OptionalValidator[T] = new OptionalV(validator)

  def collection[T](validator: Validator[T]): CollectionV[T] = new CollectionV(validator)

  implicit def pairToMap[T](pair: (String, Validator[T])): ValidationMap[T] = {
    val (key, validator) = pair
    ValidationMap(key, validator)
  }
}
