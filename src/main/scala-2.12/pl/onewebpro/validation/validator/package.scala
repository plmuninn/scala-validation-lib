package pl.onewebpro.validation

import cats.kernel.Semigroup

package object validator {

  trait Validator[S] {
    def apply(value: S): Validation[S]
  }

  trait OptionalValidator[S] extends Validator[Option[S]] {
    def validator: Validator[S]

    def apply(value: Option[S]): Validation[Option[S]] =
      value.map((v) => validator(v).map(Option.apply)).getOrElse(Validator.success(None))
  }

  trait CollectionValidator[S] extends Validator[Iterable[S]] {

    implicit val combine = new Semigroup[Iterable[S]] {
      def combine(x: Iterable[S], y: Iterable[S]): Iterable[S] = x ++ y
    }

    def validator: Validator[S]

    // TODO: this need to be done better way
    def apply(values: Iterable[S]): Validation[Iterable[S]] = {
      val results = values.map(validator.apply)
      results.tail.foldLeft(results.head.map((v) => Iterable.apply(v))) {
        case (v, vv) => v.combine(vv.map((vvv) => Iterable.apply(vvv)))
      }
    }
  }

  class OptionalV[T](val validator: Validator[T]) extends OptionalValidator[T]

  class CollectionV[T](val validator: Validator[T]) extends CollectionValidator[T]

}
