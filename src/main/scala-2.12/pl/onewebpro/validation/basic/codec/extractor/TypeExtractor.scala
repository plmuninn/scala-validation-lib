package pl.onewebpro.validation.basic.codec.extractor

import pl.onewebpro.validation.basic.codec.ParamsMap
import pl.onewebpro.validation.core.data.Extractor
import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.Validator

import scala.reflect.ClassTag


class TypeExtractor[T: ClassTag] extends Extractor[ParamsMap, T] {
  override def apply(fieldName: String, value: ParamsMap): Validation[T] =
    value.get(fieldName) match {
      case Some(fieldValue: T) => Validator.success(fieldValue)
      case _ => error
    }
}
