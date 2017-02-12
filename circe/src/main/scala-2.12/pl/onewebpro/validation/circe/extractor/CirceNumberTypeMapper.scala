package pl.onewebpro.validation.circe.extractor

import io.circe.{Json, JsonNumber}
import pl.onewebpro.validation.core.Validation

trait CirceNumberTypeMapper[T] extends CirceTypeMapper[T] {

  def apply(json: JsonNumber): Validation[T]

  override def apply(json: Json): Validation[T] =
    json.asNumber.fold(error)(apply)
}
