package pl.onewebpro.validation.circe.formatter

import io.circe.JsonNumber
import pl.onewebpro.validation.core.{Validation, Validator}


object DoubleFormatter extends CirceNumberFormatter[Double] {
  override def apply(json: JsonNumber): Validation[Double] =
    Validator.success(json.toDouble)
}
