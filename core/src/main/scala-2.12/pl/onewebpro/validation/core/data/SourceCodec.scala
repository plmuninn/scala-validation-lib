package pl.onewebpro.validation.core.data

import scala.language.implicitConversions
import scala.reflect.ClassTag

/**
  * Codec for source
  */
trait SourceCodec[T, V] {
  implicit def toSource(source: T): Source[T, V]

  implicit def stringExtractor: TypeMapper[V, String]

  implicit def shortExtractor: TypeMapper[V, Short]

  implicit def intExtractor: TypeMapper[V, Int]

  implicit def doubleExtractor: TypeMapper[V, Double]

  implicit def booleanExtractor: TypeMapper[V, Boolean]

  implicit def collectionExtractor[A: ClassTag](implicit filedMapper: TypeMapper[V, A]): TypeMapper[V, Iterable[A]]
}
