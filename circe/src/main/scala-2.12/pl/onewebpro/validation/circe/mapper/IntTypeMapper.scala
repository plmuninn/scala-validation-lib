package pl.onewebpro.validation.circe.mapper

import io.circe.JsonNumber
import pl.onewebpro.validation.core.{Validation, Validator}


object IntTypeMapper extends CirceNumberTypeMapper[Int] {
  override def apply(json: JsonNumber): Validation[Int] =
    json.toInt.fold(error)(Validator.success)
}
