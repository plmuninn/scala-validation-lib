package pl.onewebpro.validation.circe.extractor

import io.circe.JsonNumber
import pl.onewebpro.validation.core.{Validation, Validator}


object DoubleTypeMapper extends CirceNumberTypeMapper[Double] {
  override def apply(json: JsonNumber): Validation[Double] =
    Validator.success(json.toDouble)
}
