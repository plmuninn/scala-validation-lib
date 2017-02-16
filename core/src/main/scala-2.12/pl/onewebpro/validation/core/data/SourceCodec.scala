package pl.onewebpro.validation.core.data

import scala.language.implicitConversions
import scala.reflect.ClassTag

/**
  * Codec for some type of source
  *
  * @tparam T Source type
  * @tparam V Contract type - it defines type of value return by source
  */
trait SourceCodec[T, V] {
  type SourceT = T
  type Contract = V

  implicit def toSource(source: T): Source[SourceT, Contract]

  implicit def stringMapper: TypeMapper[Contract, String]

  implicit def shortMapper: TypeMapper[Contract, Short]

  implicit def intMapper: TypeMapper[Contract, Int]

  implicit def doubleMapper: TypeMapper[Contract, Double]

  implicit def booleanMapper: TypeMapper[Contract, Boolean]

  implicit def collectionMapper[A: ClassTag](implicit filedMapper: TypeMapper[Contract, A]): TypeMapper[Contract, Iterable[A]]
}
