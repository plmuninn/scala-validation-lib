package pl.onewebpro.validation.core.data

import cats.data.Validated.{Invalid, Valid}
import pl.onewebpro.validation.core.error._
import pl.onewebpro.validation.core.{FieldName, Validation, Validator}

/**
  * Map source value to result value. For example, json string will map to normal string
  *
  * @tparam S Source type for example json
  * @tparam R Result type we are expecting to get
  */
trait Formatter[S, R] {

  protected def error: Validation[R] = Validator.failure(invalidFormatError)

  def apply(value: S): Validation[R]
}

/**
  * Formatter for optional values
  */
class OptionalFormatter[S, R](formatter: Formatter[S, R]) extends Formatter[S, Option[R]] {
  override def apply(value: S): Validation[Option[R]] =
    formatter.apply(value) match {
      case Valid(v) => Validator.success(Some(v))
      case Invalid(_) => Validator.success(None)
    }
}
