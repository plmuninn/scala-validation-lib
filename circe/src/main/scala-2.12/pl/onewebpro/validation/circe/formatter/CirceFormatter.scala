package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.core.data.Formatter
import pl.onewebpro.validation.core.{FieldName, Validation}


trait CirceFormatter[T] extends Formatter[Option[Json], T] {

  protected def apply(json: Json): Validation[T]

  override def apply(value: Option[Json]): Validation[T] =
    value.map(apply).getOrElse(error)
}
