package pl.onewebpro.validation.core.entity

import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.data.{TypeMapper, Source}
import pl.onewebpro.validation.core.validator.Validator

trait ValidationMap[T] {
  def key: String

  def validate[S, V](source: Source[S, V])(implicit ex: TypeMapper[V, T]): Validation[T]
}

case class FieldMap[T](key: String, validator: Validator[T]) extends ValidationMap[T] {
  def extract[S, V](source: Source[S, V])(implicit ex: TypeMapper[V, T]): Validation[T] =
    source.extract(key)

  override def validate[S, V](source: Source[S, V])(implicit ex: TypeMapper[V, T]): Validation[T] =
    extract(source) andThen validator.apply
}
