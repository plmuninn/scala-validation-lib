package pl.onewebpro.validation.basic.codec.formatter

import pl.onewebpro.validation.core.{Validation, Validator}
import pl.onewebpro.validation.core.data.Formatter

import scala.reflect.ClassTag

class TypeFormatter[T: ClassTag] extends Formatter[Option[Any], T] {
  override def apply(value: Option[Any]): Validation[T] =
    value match {
      case Some(v: T) => Validator.success(v)
      case _ => error
    }
}
