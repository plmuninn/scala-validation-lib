package pl.onewebpro.validation.basic.codec.extractor

import pl.onewebpro.validation.basic.codec.ParamsMap
import pl.onewebpro.validation.core.{FieldName, Validation, Validator}
import pl.onewebpro.validation.core.data.Extractor

import scala.reflect.ClassTag


class CollectionTypeExtractor[T: ClassTag] extends Extractor[ParamsMap, Iterable[T]] {
  override def apply(fieldName: FieldName, value: ParamsMap): Validation[Iterable[T]] =
    value.get(fieldName) match {
      case Some(list: Iterable[T]) => Validator.success(list)
      case _ => error
    }
}
