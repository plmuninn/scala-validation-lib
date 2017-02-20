package pl.onewebpro.validation.core.entity

import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.data.{Formatter, Source}
import pl.onewebpro.validation.core.validator.Validator

/**
  * Validation map is object representing key -> validator pair.
  */
trait ValidationMap[T] {
  def key: String

  def validate[S, V](source: Source[S, V])(implicit fm: Formatter[V, T]): Validation[T]
}

/**
  * Simplest possible implementation of validation map for single type fields
  */
case class FieldMap[T](key: String, validator: Validator[T]) extends ValidationMap[T] {
  /**
    * Extract value from source
    */
  protected def extract[S, V](source: Source[S, V])(implicit fm: Formatter[V, T]): Validation[T] =
    source.extract(key)

  /**
    * Validate value for given source
    */
  override def validate[S, V](source: Source[S, V])(implicit fm: Formatter[V, T]): Validation[T] =
    extract(source) andThen validator.apply
}
