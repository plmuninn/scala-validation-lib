package pl.onewebpro.validation.core

import cats.kernel.Semigroup
import pl.onewebpro.validation.core.error.{ComposedError, ErrorWithKey, SimpleError}

package object validator {

  /**
    * Represetns validator. Validators need to be typed because they should be aware what they will validated.
    * Passing value to validator is done by Extractors.
    */
  trait Validator[S] {
    def apply(value: S): Validation[S]

    def ++(validator: Validator[S]): Validator[S] = new MultiValidator[S](Iterable(this, validator))
  }

  /**
    * Simple validator to force type of field
    */
  class TypeValidator[T] extends Validator[T] {
    override def apply(value: T): Validation[T] = Validator.success(value)
  }

  /**
    * Type for creating chain of validators
    */
  class MultiValidator[T](validators: Iterable[Validator[T]] = Iterable.empty) extends Validator[T] {

    override def ++(validator: Validator[T]): Validator[T] = new MultiValidator[T](validators ++ Iterable(validator))

    override def apply(value: T): Validation[T] = {
      validators match {
        case Nil => Validator.failure(SimpleError("error.empty_validator_list"))
        case validator :: Nil => validator.apply(value)
        case head :: tail => tail.foldLeft(head.apply(value)) {
          case (validation, v1) => validation andThen v1.apply
        }
      }
    }
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

    implicit lazy val combine = new Semigroup[Iterable[T]] {
      def combine(x: Iterable[T], y: Iterable[T]): Iterable[T] = x ++ y
    }

    private def validateValues(values: Iterable[T]): Iterable[Validation[T]] =
      values.zipWithIndex.map {
        case (value, index) => validator.apply(value)
          .leftMap(_.map(e => ComposedError(index.toString, e)))
      }

    def apply(values: Iterable[T]): Validation[Iterable[T]] =
      this.validateValues(values) match {
        case Nil => Validator.success(Iterable.empty)
        case head :: Nil => head.map(Iterable.apply(_))
        // TODO: this need to be done better way
        case head :: tail => tail.foldLeft(head.map(Iterable.apply(_))) {
          case (v1, v2) => v1.combine(v2.map(Iterable.apply(_)))
        }
      }
  }

  class NonEmptyCollectionValidator[T](validator: Validator[T]) extends CollectionValidator[T](validator) {
    private def nonEmpty(values: Iterable[T]): Validation[Iterable[T]] =
      if (values.isEmpty) {
        Validator.failure(SimpleError("error.empty_list"))
      } else {
        Validator.success(values)
      }

    override def apply(values: Iterable[T]): Validation[Iterable[T]] =
      super.apply(values) andThen nonEmpty
  }

}
