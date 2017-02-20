package pl.onewebpro.validation.circe.formatter

import io.circe.JsonNumber
import pl.onewebpro.validation.core.{Validation, Validator}


object ShortFormatter extends CirceNumberFormatter[Short] {
  override def apply(json: JsonNumber): Validation[Short] =
    json.toShort.fold(error)(Validator.success)
}
