package pl.onewebpro.validation.basic.codec.formatter

import pl.onewebpro.validation.core.data.Formatter
import pl.onewebpro.validation.core.{Validation, Validator}

import scala.reflect.ClassTag

class CollectionTypeFormatter[T: ClassTag](implicit formatter: Formatter[Any, T]) extends Formatter[Any, Iterable[T]] {
  override def apply(value: Any): Validation[Iterable[T]] =
    value match {
        // We need to check is T is really T because Iterable[T] is contravariant (+T)
      case list: Iterable[T] => list.map(value => formatter.apply(value)).swap
      case _ => error
    }
}
