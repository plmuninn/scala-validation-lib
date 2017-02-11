package pl.onewebpro.validation.circe.extractor

import io.circe.{JsonDouble, JsonNumber}
import pl.onewebpro.validation.core.{Validation, Validator}


object DoubleExtractor extends CirceNumberExtractor[Double] {
  override def apply(json: JsonNumber): Validation[Double] =
    json match {
      case JsonDouble(double) => Validator.success(double)
      case _ => error
    }
}
