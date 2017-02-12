package pl.onewebpro.validation.basic.codec.extractor

import pl.onewebpro.validation.core.data.TypeMapper
import pl.onewebpro.validation.core.{Validation, Validator}

import scala.reflect.ClassTag

class CollectionTypeTypeMapper[T: ClassTag] extends TypeMapper[Option[Any], Iterable[T]] {
  override def apply(value: Option[Any]): Validation[Iterable[T]] =
    value match {
      case Some(list: Iterable[T]) => Validator.success(list)
      case _ => error
    }
}
