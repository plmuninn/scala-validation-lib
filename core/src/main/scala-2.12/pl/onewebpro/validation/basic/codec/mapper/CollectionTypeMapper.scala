package pl.onewebpro.validation.basic.codec.mapper

import pl.onewebpro.validation.core.data.TypeMapper
import pl.onewebpro.validation.core.{Validation, Validator}

import scala.reflect.ClassTag

class CollectionTypeMapper[T: ClassTag](implicit mapper: TypeMapper[Option[Any], T]) extends TypeMapper[Option[Any], Iterable[T]] {
  override def apply(value: Option[Any]): Validation[Iterable[T]] =
    value match {
      case Some(list: Iterable[T]) => list.map(value => mapper.apply(Option(value))).swap
      case _ => error
    }
}
