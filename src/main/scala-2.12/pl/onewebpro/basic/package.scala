package pl.onewebpro

import pl.onewebpro.basic.extractors.{IntegerExtractor, OptionalStringExtractor, StringExtractor}
import pl.onewebpro.basic.validators.{NonEmptyStringFieldValidator, TextFieldValidator}
import pl.onewebpro.validation.core.data.Source
import pl.onewebpro.validation.core.validator.{CollectionV, OptionalV, OptionalValidator, Validator}

package object basic {
  type ParamsMap = Map[String, String]

  implicit def paramsToSource(source: ParamsMap): Source[ParamsMap] = new ParamsMapSource(source)

  implicit class ParamsMapImplicits(map: ParamsMap) {
    def toSource: Source[ParamsMap] = map
  }

  implicit val stringExtractor = StringExtractor
  implicit val optionalStringExtractor = OptionalStringExtractor
  implicit val integerStringExtractor = new IntegerExtractor

  val nonEmptyString = NonEmptyStringFieldValidator

  def textValidator(min: Int = 0, max: Int = 0): TextFieldValidator = new TextFieldValidator(min, max)

  val textValidator: TextFieldValidator = textValidator()

  def optional[T](validator: Validator[T]): OptionalValidator[T] = new OptionalV(validator)

  def collection[T](validator: Validator[T]): CollectionV[T] = new CollectionV(validator)
}
