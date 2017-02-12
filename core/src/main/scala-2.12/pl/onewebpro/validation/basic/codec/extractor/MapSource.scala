package pl.onewebpro.validation.basic.codec.extractor

import pl.onewebpro.validation.basic.codec.ParamsMap
import pl.onewebpro.validation.core.FieldName
import pl.onewebpro.validation.core.data.Source


class MapSource(val source: ParamsMap) extends Source[ParamsMap, Option[Any]] {

  override def findByName(fieldName: FieldName): Option[Any] = source.get(fieldName)
}
