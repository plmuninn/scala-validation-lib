package pl.onewebpro.validation.circe

import io.circe.Json
import pl.onewebpro.validation.circe.extractor._
import pl.onewebpro.validation.core.data.{Extractor, SourceCodec}

import scala.reflect.ClassTag

object Codec extends SourceCodec[Json] {
  override implicit val stringExtractor: Extractor[Json, String] = StringExtractor

  override implicit val shortExtractor: Extractor[Json, Short] = ShortExtractor

  override implicit val intExtractor: Extractor[Json, Int] = IntExtractor

  override implicit val doubleExtractor: Extractor[Json, Double] = DoubleExtractor

  override implicit val booleanExtractor: Extractor[Json, Boolean] = BooleanExtractor

  override implicit def collectionExtractor[A: ClassTag]: Extractor[Json, Iterable[A]] = ???
}
