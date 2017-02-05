package pl.onewebpro.validation

import cats.data.{NonEmptyList, Validated}

object Validator {
  def success[T](t: T): Validation[T] = Validated.valid(t)

  def failure[T](err: String): Validation[T] = Validated.invalidNel(err)

  def failureOf[T](errs: List[String]): Validation[T] = Validated.Invalid(NonEmptyList(errs.head, errs.tail))
}

