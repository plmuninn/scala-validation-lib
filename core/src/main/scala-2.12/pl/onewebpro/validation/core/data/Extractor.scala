package pl.onewebpro.validation.core.data

import cats.data.Validated.{Invalid, Valid}
import pl.onewebpro.validation.core.error._
import pl.onewebpro.validation.core.{FieldName, Validation, Validator}

/**
  * Extracts specific type from source. For example, from json it will extract string type of json
  *
  * @tparam S Source type for example json
  * @tparam R Result type we are expecting to get
  */
trait Extractor[S, R] {

  protected def error: Validation[R] = Validator.failure(invalidTypeError)

  def apply(fieldName: FieldName, value: S): Validation[R]
}

/**
  * Extractor for optional values
  */
class OptionalExtractor[S, R](extractor: Extractor[S, R]) extends Extractor[S, Option[R]] {
  override def apply(fieldName: FieldName, value: S): Validation[Option[R]] =
    extractor.apply(fieldName, value) match {
      case Valid(v) => Validator.success(Some(v))
      case Invalid(_) => Validator.success(None)
    }
}
