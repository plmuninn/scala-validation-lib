package pl.onewebpro.validation.circe.formatter

import io.circe.{Json, JsonNumber}
import pl.onewebpro.validation.core.Validation

trait CirceNumberFormatter[T] extends CirceFormatter[T] {

  protected def apply(json: JsonNumber): Validation[T]

  override def apply(json: Json): Validation[T] =
    json.asNumber.fold(error)(apply)
}
