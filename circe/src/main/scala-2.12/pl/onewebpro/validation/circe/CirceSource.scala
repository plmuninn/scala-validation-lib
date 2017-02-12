package pl.onewebpro.validation.circe

import io.circe.Json
import pl.onewebpro.validation.core.FieldName
import pl.onewebpro.validation.core.data.Source

class CirceSource(val source: Json) extends Source[Json, Option[Json]] {
  override def findByName(fieldName: FieldName): Option[Json] = source.asObject.flatMap(obj => obj(fieldName))
}
