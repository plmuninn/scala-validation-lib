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
trait TypeMapper[S, R] {

  protected def error: Validation[R] = Validator.failure(invalidTypeError)

  def apply(value: S): Validation[R]
}

/**
  * Extractor for optional values
  */
class OptionalTypeMapper[S, R](mapper: TypeMapper[S, R]) extends TypeMapper[S, Option[R]] {
  override def apply(value: S): Validation[Option[R]] =
    mapper.apply(value) match {
      case Valid(v) => Validator.success(Some(v))
      case Invalid(_) => Validator.success(None)
    }
}
