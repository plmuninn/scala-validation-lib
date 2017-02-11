package pl.onewebpro.validation.circe.extractor

import io.circe.Json
import pl.onewebpro.validation.core.{Validation, Validator}

object BooleanExtractor extends CirceExtractor[Boolean] {
  override def apply(json: Json): Validation[Boolean] =
    json.asBoolean.fold(error)(Validator.success)
}
