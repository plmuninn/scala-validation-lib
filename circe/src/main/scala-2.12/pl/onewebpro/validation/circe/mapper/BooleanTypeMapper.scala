package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import pl.onewebpro.validation.core.{Validation, Validator}

object BooleanTypeMapper extends CirceTypeMapper[Boolean] {
  override def apply(json: Json): Validation[Boolean] =
    json.asBoolean.fold(error)(Validator.success)
}
