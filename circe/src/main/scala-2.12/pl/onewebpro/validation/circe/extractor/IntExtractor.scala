package pl.onewebpro.validation.circe.extractor

import io.circe.JsonNumber
import pl.onewebpro.validation.core.{Validation, Validator}


object IntExtractor extends CirceNumberExtractor[Int] {
  override def apply(json: JsonNumber): Validation[Int] =
    json.toInt.fold(error)(Validator.success)
}
