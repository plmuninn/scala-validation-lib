package pl.onewebpro.validation

import cats.data.{NonEmptyList, Validated}
import pl.onewebpro.validation.entity.ValidationError


object Validator {
  def success[T](t: T): Validation[T] = Validated.valid(t)

  def failure[T](err: ValidationError): Validation[T] = Validated.invalidNel(err)

  def failureOf[T](errs: List[ValidationError]): Validation[T] = Validated.Invalid(NonEmptyList(errs.head, errs.tail))
}
