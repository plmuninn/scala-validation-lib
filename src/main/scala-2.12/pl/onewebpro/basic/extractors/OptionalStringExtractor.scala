package pl.onewebpro.basic.extractors

import pl.onewebpro.basic.ParamsMap
import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.Validator
import pl.onewebpro.validation.core.data.Extractor

object OptionalStringExtractor extends Extractor[ParamsMap, Option[String]] {
  override def apply(fieldName: String, value: ParamsMap): Validation[Option[String]] =
    Validator.success(value.get(fieldName))
}
