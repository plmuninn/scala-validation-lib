package pl.onewebpro.validation.core.entity

import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.data.{Extractor, Source}
import pl.onewebpro.validation.core.validator.Validator

trait ValidationMap[T] {
  def key: String

  def validate[S](source: Source[S])(implicit ex: Extractor[S, T]): Validation[T]
}

case class FieldMap[T](key: String, validator: Validator[T]) extends ValidationMap[T] {
  def extract[S](source: Source[S])(implicit ex: Extractor[S, T]): Validation[T] =
    source.extract(key)

  override def validate[S](source: Source[S])(implicit ex: Extractor[S, T]): Validation[T] =
    extract(source) andThen validator.apply
}
