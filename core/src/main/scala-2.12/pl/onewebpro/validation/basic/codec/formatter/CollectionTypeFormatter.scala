package pl.onewebpro.validation.basic.codec.formatter

import pl.onewebpro.validation.core.data.Formatter
import pl.onewebpro.validation.core.{Validation, Validator}

import scala.reflect.ClassTag

class CollectionTypeFormatter[T: ClassTag](implicit formatter: Formatter[Option[Any], T]) extends Formatter[Option[Any], Iterable[T]] {
  override def apply(value: Option[Any]): Validation[Iterable[T]] =
    value match {
      case Some(list: Iterable[T]) => list.map(value => formatter.apply(Option(value))).swap
      case _ => error
    }
}
