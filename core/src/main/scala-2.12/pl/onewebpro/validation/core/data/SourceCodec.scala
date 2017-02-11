package pl.onewebpro.validation.core.data

import scala.language.implicitConversions
import scala.reflect.ClassTag

/**
  * Codec for source
  */
trait SourceCodec[T] {
  implicit def toSource(source: T): Source[T] = new Source[T](source)

  implicit def stringExtractor: Extractor[T, String]

  implicit def shortExtractor: Extractor[T, Short]

  implicit def intExtractor: Extractor[T, Int]

  implicit def doubleExtractor: Extractor[T, Double]

  implicit def booleanExtractor: Extractor[T, Boolean]

  implicit def collectionExtractor[A: ClassTag]: Extractor[T, Iterable[A]]
}
