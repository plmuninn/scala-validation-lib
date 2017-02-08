package pl.onewebpro.validation.core

import cats.kernel.Semigroup

package object validator {

  /**
    * Represetns validator. Validators need to be typed because they should be aware what they will validated.
    * Passing value to validator is done by Extractors.
    */
  trait Validator[S] {
    def apply(value: S): Validation[S]
  }

  /**
    * Simple validator to force type of field
    */
  class TypeValidator[T] extends Validator[T] {
    override def apply(value: T): Validation[T] = Validator.success(value)
  }

  /**
    * Validator that handles options
    */
  class OptionalValidator[T](val validator: Validator[T]) extends Validator[Option[T]] {
    def apply(value: Option[T]): Validation[Option[T]] =
      value.map((v) => validator(v).map(Option.apply)).getOrElse(Validator.success(None))
  }

  /**
    * Validator for collections
    */
  class CollectionValidator[T](val validator: Validator[T]) extends Validator[Iterable[T]] {

    implicit val combine = new Semigroup[Iterable[T]] {
      def combine(x: Iterable[T], y: Iterable[T]): Iterable[T] = x ++ y
    }

    // TODO: this need to be done better way
    def apply(values: Iterable[T]): Validation[Iterable[T]] = {
      val results = values.map(validator.apply)
      results.tail.foldLeft(results.head.map((v) => Iterable.apply(v))) {
        case (v, vv) => v.combine(vv.map((vvv) => Iterable.apply(vvv)))
      }
    }
  }

}
