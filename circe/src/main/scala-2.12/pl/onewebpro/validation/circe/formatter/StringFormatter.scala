package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.core.{Validation, Validator}

object StringFormatter extends CirceFormatter[String] {
  override protected def apply(json: Json): Validation[String] =
    json.asString.fold(error)(Validator.success)
}
