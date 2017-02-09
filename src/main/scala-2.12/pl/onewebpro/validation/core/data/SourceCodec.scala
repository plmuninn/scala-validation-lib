package pl.onewebpro.validation.core.data

import scala.language.implicitConversions

/**
  * Codec for source
  */
trait SourceCodec[T] {
  implicit def toSource(source: T): Source[T] = new Source[T](source)

  implicit def stringExtractor: Extractor[T, String]

  implicit def shortExtractor: Extractor[T, Short]

  implicit def intExtractor: Extractor[T, Int]

  implicit def doubleExtractor: Extractor[T, Double]

  implicit def floatExtractor: Extractor[T, Float]

  implicit def booleanExtractor: Extractor[T, Boolean]

  implicit def collectionExtractor[A]: Extractor[T, Iterable[A]]
}
