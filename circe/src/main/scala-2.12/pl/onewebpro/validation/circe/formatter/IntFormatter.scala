package pl.onewebpro.validation.circe.formatter

import io.circe.JsonNumber
import pl.onewebpro.validation.core.{Validation, Validator}


object IntFormatter extends CirceNumberFormatter[Int] {
  override def apply(json: JsonNumber): Validation[Int] =
    json.toInt.fold(error)(Validator.success)
}
