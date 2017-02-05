package pl.onewebpro.validation.entity

import pl.onewebpro.validation.Validation
import pl.onewebpro.validation.data.{Extractor, Source}
import pl.onewebpro.validation.validator.Validator

case class ValidationMap[T](key: String, validator: Validator[T]) {
  def extract[S](source: Source[S])(implicit ex: Extractor[S, T]): Validation[T] =
    source.extract(key)

  def validate[S](source: Source[S])(implicit ex: Extractor[S, T]): Validation[T] =
    extract(source) andThen validator.apply
}
