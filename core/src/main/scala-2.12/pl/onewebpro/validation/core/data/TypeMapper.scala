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
trait TypeMapper[S, R] {

  protected def error: Validation[R] = Validator.failure(invalidTypeError)

  def apply(value: S): Validation[R]
}

/**
  * Mapper for optional values
  */
class OptionalTypeMapper[S, R](mapper: TypeMapper[S, R]) extends TypeMapper[S, Option[R]] {
  override def apply(value: S): Validation[Option[R]] =
    mapper.apply(value) match {
      case Valid(v) => Validator.success(Some(v))
      case Invalid(_) => Validator.success(None)
    }
}
