package pl.onewebpro.validation.circe.extractor

import io.circe.Json
import pl.onewebpro.validation.core.data.Extractor
import pl.onewebpro.validation.core.{FieldName, Validation}


trait CirceExtractor[T] extends Extractor[Json, T] {

  def apply(json: Json): Validation[T]

  override def apply(fieldName: FieldName, value: Json): Validation[T] =
    value.asObject.flatMap(jsonObject => jsonObject(fieldName)).map(apply).getOrElse(error)
}
