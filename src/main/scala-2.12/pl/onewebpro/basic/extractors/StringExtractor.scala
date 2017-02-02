package pl.onewebpro.basic.extractors

import pl.onewebpro.basic.ParamsMap
import pl.onewebpro.validation.data.Extractor
import pl.onewebpro.validation.{Validation, Validator}


object StringExtractor extends Extractor[ParamsMap, String] {
  override def apply(fieldName: String, value: ParamsMap): Validation[String] =
    value.get(fieldName) match {
      case Some(fieldValue) => Validator.success(fieldValue)
      case _ => error
    }
}
