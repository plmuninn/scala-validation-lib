package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.core.{Validation, Validator}

object BooleanFormatter extends CirceFormatter[Boolean] {
  override protected def apply(json: Json): Validation[Boolean] =
    json.asBoolean.fold(error)(Validator.success)
}
