package pl.onewebpro.basic.extractors

import pl.onewebpro.basic.ParamsMap
import pl.onewebpro.validation.{Validation, Validator}
import pl.onewebpro.validation.data.Extractor

object OptionalStringExtractor extends Extractor[ParamsMap, Option[String]] {
  override def apply(fieldName: String, value: ParamsMap): Validation[Option[String]] =
    Validator.success(value.get(fieldName))
}
