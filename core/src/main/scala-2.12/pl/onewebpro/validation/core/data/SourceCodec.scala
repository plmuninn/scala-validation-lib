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

  implicit def stringFormat: Formatter[Contract, String]

  implicit def shortFormat: Formatter[Contract, Short]

  implicit def intFormat: Formatter[Contract, Int]

  implicit def doubleFormat: Formatter[Contract, Double]

  implicit def booleanFormat: Formatter[Contract, Boolean]

  implicit def collectionFormat[A: ClassTag](implicit filedFormat: Formatter[Contract, A]): Formatter[Contract, Iterable[A]]
}
