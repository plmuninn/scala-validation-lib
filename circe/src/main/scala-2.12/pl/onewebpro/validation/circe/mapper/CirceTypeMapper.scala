package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import pl.onewebpro.validation.core.data.TypeMapper
import pl.onewebpro.validation.core.{FieldName, Validation}


trait CirceTypeMapper[T] extends TypeMapper[Option[Json], T] {

  protected def apply(json: Json): Validation[T]

  override def apply(value: Option[Json]): Validation[T] =
    value.map(apply).getOrElse(error)
}
