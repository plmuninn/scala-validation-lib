package pl.onewebpro.validation.basic.codec.extractor

import pl.onewebpro.validation.core.{Validation, Validator}
import pl.onewebpro.validation.core.data.TypeMapper

import scala.reflect.ClassTag

class TypeTypeMapper[T: ClassTag] extends TypeMapper[Option[Any], T] {
  override def apply(value: Option[Any]): Validation[T] =
    value match {
      case Some(v: T) => Validator.success(v)
      case _ => error
    }
}
