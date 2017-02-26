package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.core.data.Formatter
import pl.onewebpro.validation.core.{Validation, Validator}

object BooleanFormatter extends Formatter[Json, Boolean] {
  override def apply(json: Json): Validation[Boolean] =
    json.asBoolean.fold(error)(Validator.success)
}
