package pl.onewebpro.validation.schema

import pl.onewebpro.validation.{FieldName, Validated}
import pl.onewebpro.validation.data.{Extractor, Source}
import pl.onewebpro.validation.entity.ValidationError
import pl.onewebpro.validation.validator.Validator


trait Schema[R] {
  def bind[S, A](fieldName: FieldName, validator: Validator[A], source: Source[S])
                (implicit extractor: Extractor[S, A]): Validated[A] =
    (source.extract[A](fieldName) andThen validator.apply)
      .leftMap(values => values.map(ValidationError(fieldName, _)))
}

object Schema {
  def apply[R, A1](a1: (String, Validator[A1]))(apply: (A1) => R)(unapply: (R) => Option[(A1)]): Schema1[R, A1] =
    new Schema1(apply, unapply, a1)

  def apply[R, A1, A2](a1: (String, Validator[A1]), a2: (String, Validator[A2]))
                      (apply: (A1, A2) => R)(unapply: (R) => Option[(A1, A2)]): Schema2[R, A1, A2] =
    new Schema2(apply, unapply, a1, a2)
}

class Schema1[R, A1](apply: (A1) => R, unapply: (R) => Option[A1], a1: (String, Validator[A1])) extends Schema[R] {
  def validate[T](source: T)(implicit f: (T) => Source[T], ex1: Extractor[T, A1]): Validated[R] = {
    val s = f(source)
    val (n1, v1) = a1
    bind(n1, v1, s).map(apply)
  }
}

class Schema2[R, A1, A2]
(apply: (A1, A2) => R, unapply: (R) => Option[(A1, A2)], a1: (String, Validator[A1]), a2: (String, Validator[A2])) extends Schema[R] {

  def validate[T](source: T)(implicit f: (T) => Source[T], ex1: Extractor[T, A1], ex2: Extractor[T, A2]): Validated[R] = {
    import cats.implicits._
    val s = f(source)
    val (n1, v1) = a1
    val (n2, v2) = a2
    (bind(n1, v1, s) |@| bind(n2, v2, s)).map(apply)
  }
}



