package pl.onewebpro.validation.core.entity

import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.data.{Extractor, Source}
import pl.onewebpro.validation.core.validator.Validator

case class ValidationMap[T](key: String, validator: Validator[T]) {
  def extract[S](source: Source[S])(implicit ex: Extractor[S, T]): Validation[T] =
    source.extract(key)

  def validate[S](source: Source[S])(implicit ex: Extractor[S, T]): Validation[T] =
    extract(source) andThen validator.apply
}
