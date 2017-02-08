package pl.onewebpro.validation.core

import cats.data.{NonEmptyList, Validated => CatsValidator}
import pl.onewebpro.validation.core.entity.ErrorValue

object Validator {
  def success[T](t: T): Validation[T] = CatsValidator.valid(t)

  def failure[T](err: ErrorValue): Validation[T] = CatsValidator.invalidNel(err)

  def failureOf[T](errs: List[ErrorValue]): Validation[T] = CatsValidator.Invalid(NonEmptyList(errs.head, errs.tail))
}

