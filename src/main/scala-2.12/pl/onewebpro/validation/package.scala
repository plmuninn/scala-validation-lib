package pl.onewebpro

import pl.onewebpro.validation.basic.validators.{NonEmptyStringFieldValidator, TextFieldValidator}
import pl.onewebpro.validation.core.entity.ValidationMap
import pl.onewebpro.validation.core.schema.Schema
import pl.onewebpro.validation.core.validator._

import scala.language.implicitConversions

package object validation {

  lazy val schema = Schema

  lazy val nonEmptyString = NonEmptyStringFieldValidator

  def textValidator(min: Int = 0, max: Int = 0): TextFieldValidator = new TextFieldValidator(min, max)

  lazy val textValidator: TextFieldValidator = textValidator()

  def optional[T](validator: Validator[T]): OptionalValidator[T] = new OptionalValidator(validator)

  def collection[T](validator: Validator[T]): CollectionValidator[T] = new CollectionValidator(validator)

  def multi[T](validators: Validator[T]*): MultiValidator[T] = new MultiValidator[T](validators)

  def of[T]: TypeValidator[T] = new TypeValidator[T]

  implicit def pairToMap[T](pair: (String, Validator[T])): ValidationMap[T] = {
    val (key, validator) = pair
    ValidationMap(key, validator)
  }
}
