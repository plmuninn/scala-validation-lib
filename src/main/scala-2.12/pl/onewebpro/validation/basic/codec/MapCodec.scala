package pl.onewebpro.validation.basic.codec

import pl.onewebpro.validation.basic.codec.extractor.TypeExtractor
import pl.onewebpro.validation.core.data.{Extractor, SourceCodec}


object MapCodec extends SourceCodec[ParamsMap] {
  override implicit lazy val stringExtractor: Extractor[ParamsMap, String] = new TypeExtractor
  override implicit lazy val shortExtractor: Extractor[ParamsMap, Short] = new TypeExtractor
  override implicit lazy val intExtractor: Extractor[ParamsMap, Int] = new TypeExtractor
  override implicit lazy val doubleExtractor: Extractor[ParamsMap, Double] = new TypeExtractor
  override implicit lazy val floatExtractor: Extractor[ParamsMap, Float] = new TypeExtractor
  override implicit lazy val booleanExtractor: Extractor[ParamsMap, Boolean] = new TypeExtractor

  override implicit def collectionExtractor[A]: Extractor[ParamsMap, Iterable[A]] = ???
}
