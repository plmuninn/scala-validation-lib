package pl.onewebpro.validation.schema

import pl.onewebpro.validation.Validated
import pl.onewebpro.validation.data.{Extractor, Source}
import pl.onewebpro.validation.entity.{ValidationError, ValidationMap}


trait Schema[R] {
  def bind[S, A](field: ValidationMap[A], source: Source[S])
                (implicit extractor: Extractor[S, A]): Validated[A] =
    (source.extract[A](field.key) andThen field.validator.apply)
      .leftMap(values => values.map(ValidationError(field.key, _)))
}

object Schema {
  def apply[R, A1](a1: ValidationMap[A1])(apply: (A1) => R)(unapply: (R) => Option[(A1)]): Schema1[R, A1] =
    new Schema1(apply, unapply, a1)

  def apply[R, A1, A2](a1: ValidationMap[A1], a2: ValidationMap[A2])
                      (apply: (A1, A2) => R)(unapply: (R) => Option[(A1, A2)]): Schema2[R, A1, A2] =
    new Schema2(apply, unapply, a1, a2)
}

class Schema1[R, A1](apply: (A1) => R, unapply: (R) => Option[A1], a1: ValidationMap[A1]) extends Schema[R] {
  def validate[T](source: T)(implicit f: (T) => Source[T], ex1: Extractor[T, A1]): Validated[R] = {
    val s = f(source)
    bind(a1, s).map(apply)
  }
}

class Schema2[R, A1, A2]
(apply: (A1, A2) => R, unapply: (R) => Option[(A1, A2)], a1: ValidationMap[A1], a2: ValidationMap[A2]) extends Schema[R] {

  def validate[T](source: T)(implicit f: (T) => Source[T], ex1: Extractor[T, A1], ex2: Extractor[T, A2]): Validated[R] = {
    import cats.implicits._
    val s = f(source)
    (bind(a1, s) |@| bind(a2, s)).map(apply)
  }
}



