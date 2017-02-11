package pl.onewebpro.validation.basic.codec

import pl.onewebpro.validation.basic.codec.extractor.{CollectionTypeExtractor, TypeExtractor}
import pl.onewebpro.validation.core.data.{Extractor, SourceCodec}
import scala.reflect._

object MapCodec extends SourceCodec[ParamsMap] {
  override implicit lazy val stringExtractor: Extractor[ParamsMap, String] = new TypeExtractor
  override implicit lazy val shortExtractor: Extractor[ParamsMap, Short] = new TypeExtractor
  override implicit lazy val intExtractor: Extractor[ParamsMap, Int] = new TypeExtractor
  override implicit lazy val doubleExtractor: Extractor[ParamsMap, Double] = new TypeExtractor
  override implicit lazy val booleanExtractor: Extractor[ParamsMap, Boolean] = new TypeExtractor

  override implicit def collectionExtractor[A: ClassTag]: Extractor[ParamsMap, Iterable[A]] =
    new CollectionTypeExtractor[A]
}
