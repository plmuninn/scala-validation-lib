package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.core.data.Formatter
import pl.onewebpro.validation.core.{Validation, Validator}

object StringFormatter extends Formatter[Json, String] {
  override def apply(json: Json): Validation[String] =
    json.asString.fold(error)(Validator.success)
}
