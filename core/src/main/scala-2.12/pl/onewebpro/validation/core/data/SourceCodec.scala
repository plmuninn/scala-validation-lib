package pl.onewebpro.validation.core.data

import scala.language.implicitConversions
import scala.reflect.ClassTag

/**
  * Codec for source
  */
trait SourceCodec[T, V] {
  implicit def toSource(source: T): Source[T, V]

  implicit def stringMapper: TypeMapper[V, String]

  implicit def shortMapper: TypeMapper[V, Short]

  implicit def intMapper: TypeMapper[V, Int]

  implicit def doubleMapper: TypeMapper[V, Double]

  implicit def booleanMapper: TypeMapper[V, Boolean]

  implicit def collectionMapper[A: ClassTag](implicit filedMapper: TypeMapper[V, A]): TypeMapper[V, Iterable[A]]
}
