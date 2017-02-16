package pl.onewebpro.validation.core

import cats.data.Validated.Valid
import pl.onewebpro.validation._
import pl.onewebpro.validation.core.error.{ComposedError, _}

package object validator {

  /**
    * Represetns validator. Validators need to be typed because they should be aware what they will validated.
    * Passing value to validator is done by Extractors.
    */
  trait Validator[S] {
    def apply(value: S): Validation[S]

    // scalastyle:off
    def +(validator: Validator[S]): Validator[S] = new MultiValidator[S](Iterable(this, validator))

    def ++(validator: Validator[S]*): Validator[S] = new MultiValidator[S](Iterable(this) ++ validator)

    def ||(validator: Validator[S]): Validator[S] = new OrValidator(this, validator)

    // scalastyle:on
  }

  /**
    * Simple validator to force type of field
    */
  class TypeValidator[T] extends Validator[T] {
    override def apply(value: T): Validation[T] = Validator.success(value)
  }

  /**
    * Type for creating chain of validators. All of them need to return Valid[T] otherwise value is invalid.
    */
  class MultiValidator[T](validators: Iterable[Validator[T]] = Iterable.empty) extends Validator[T] {
    // scalastyle:off
    override def +(validator: Validator[T]): Validator[T] = new MultiValidator[T](validators ++ Iterable(validator))

    override def ++(validator: Validator[T]*): Validator[T] = new MultiValidator[T](validators ++ validator)

    // scalastyle:on

    override def apply(value: T): Validation[T] = {
      validators match {
        case Nil => Validator.failure(multiValidatorError)
        case validator :: Nil => validator.apply(value)
        case _ => validators.tail.foldLeft(validators.head.apply(value)) {
          case (validation, v1) => validation andThen v1.apply
        }
      }
    }
  }

  /**
    * Validator that will work like `or`, `||`. It will return Valid[T] if left or right validator will return Valid[T]
    */
  class OrValidator[T](left: Validator[T], right: Validator[T]) extends Validator[T] {
    override def apply(value: T): Validation[T] =
      left.apply(value) match {
        case Valid(v) => Validator.success(v)
        case _ => right.apply(value)
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
    * Validator for collections. It will consider empty collection a valid collection
    */
  class CollectionValidator[T](val validator: Validator[T]) extends Validator[Iterable[T]] {
    private def validateValues(values: Iterable[T]): Iterable[Validation[T]] =
      values.zipWithIndex.map {
        case (value, index) => validator.apply(value)
          .leftMap(_.map(e => ComposedError(index.toString, e)))
      }

    def apply(values: Iterable[T]): Validation[Iterable[T]] =
      this.validateValues(values).swap
  }

  /**
    * Validator for non empty collections
    */
  class NonEmptyCollectionValidator[T](validator: Validator[T]) extends CollectionValidator[T](validator) {
    private def nonEmpty(values: Iterable[T]): Validation[Iterable[T]] =
      if (values.isEmpty) {
        Validator.failure(nonEmptyListError)
      } else {
        Validator.success(values)
      }

    override def apply(values: Iterable[T]): Validation[Iterable[T]] =
      super.apply(values) andThen nonEmpty
  }

}
