package pl.onewebpro.validation.circe.extractor

import io.circe.JsonNumber
import pl.onewebpro.validation.core.{Validation, Validator}


object ShortExtractor extends CirceNumberExtractor[Short] {
  override def apply(json: JsonNumber): Validation[Short] =
    json.toShort.fold(error)(Validator.success)
}
