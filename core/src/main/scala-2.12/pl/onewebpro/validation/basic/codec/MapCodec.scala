package pl.onewebpro.validation.basic.codec

import pl.onewebpro.validation.basic.codec.formatter.{CollectionTypeFormatter, TypeFormatter}
import pl.onewebpro.validation.core.data.{Source, SourceCodec, Formatter}

import scala.language.implicitConversions
import scala.reflect._

object MapCodec extends SourceCodec[ParamsMap, Any] {
  override implicit def toSource(source: ParamsMap): Source[SourceT, Contract] = new MapSource(source)

  override implicit lazy val stringFormat: Formatter[Contract, String] = new TypeFormatter[String]
  override implicit lazy val shortFormat: Formatter[Contract, Short] = new TypeFormatter[Short]
  override implicit lazy val intFormat: Formatter[Contract, Int] = new TypeFormatter[Int]
  override implicit lazy val doubleFormat: Formatter[Contract, Double] = new TypeFormatter[Double]
  override implicit lazy val booleanFormat: Formatter[Contract, Boolean] = new TypeFormatter[Boolean]

  override implicit def collectionFormat[A: ClassTag]
  (implicit fieldFormatter: Formatter[Contract, A]): Formatter[Contract, Iterable[A]] =
    new CollectionTypeFormatter[A]
}
