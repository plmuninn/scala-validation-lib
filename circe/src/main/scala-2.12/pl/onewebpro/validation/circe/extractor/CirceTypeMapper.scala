package pl.onewebpro.validation.circe.extractor

import io.circe.Json
import pl.onewebpro.validation.core.data.TypeMapper
import pl.onewebpro.validation.core.{FieldName, Validation}


trait CirceTypeMapper[T] extends TypeMapper[Option[Json], T] {

  def apply(json: Json): Validation[T]

  override def apply(value: Option[Json]): Validation[T] =
    value.map(apply).getOrElse(error)
}
