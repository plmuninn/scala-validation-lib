package pl.onewebpro.validation.core.data

import pl.onewebpro.validation.core.error._
import pl.onewebpro.validation.core.{Validation, Validator}

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

  def apply(value: Option[S]): Validation[Option[R]] = value match {
    case Some(v: S) => this.apply(v)
    case _ => Validator.success(None)
  }

  override def apply(value: S): Validation[Option[R]] = value match {
    case Some(_: S) => this.apply(value.asInstanceOf[Option[S]])
    case _ => formatter.apply(value).map(Option.apply)
  }
}
