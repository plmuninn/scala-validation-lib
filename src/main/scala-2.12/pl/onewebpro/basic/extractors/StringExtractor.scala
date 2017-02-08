package pl.onewebpro.basic.extractors

import pl.onewebpro.basic.ParamsMap
import pl.onewebpro.validation.core.data.Extractor
import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.Validator


object StringExtractor extends Extractor[ParamsMap, String] {
  override def apply(fieldName: String, value: ParamsMap): Validation[String] =
    value.get(fieldName) match {
      case Some(fieldValue) => Validator.success(fieldValue)
      case _ => error
    }
}
