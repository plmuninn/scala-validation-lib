package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import pl.onewebpro.validation.core.{Validation, Validator}

object StringTypeMapper extends CirceTypeMapper[String] {
  override protected def apply(json: Json): Validation[String] =
    json.asString.fold(error)(Validator.success)
}
