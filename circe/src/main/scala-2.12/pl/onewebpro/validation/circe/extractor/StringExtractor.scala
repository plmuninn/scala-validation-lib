package pl.onewebpro.validation.circe.extractor

import io.circe.Json
import pl.onewebpro.validation.core.{Validation, Validator}

object StringExtractor extends CirceExtractor[String] {
  override def apply(json: Json): Validation[String] =
    json.asString.fold(error)(Validator.success)
}
