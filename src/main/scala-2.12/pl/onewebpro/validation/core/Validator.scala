package pl.onewebpro.validation.core

import cats.data.{NonEmptyList, Validated => CatsValidator}

object Validator {
  def success[T](t: T): Validation[T] = CatsValidator.valid(t)

  def failure[T](err: String): Validation[T] = CatsValidator.invalidNel(err)

  def failureOf[T](errs: List[String]): Validation[T] = CatsValidator.Invalid(NonEmptyList(errs.head, errs.tail))
}

