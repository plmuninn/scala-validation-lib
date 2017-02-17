package pl.onewebpro.validation.basic.codec

import pl.onewebpro.validation.basic.codec.mapper.{CollectionTypeMapper, BasicTypeMapper}
import pl.onewebpro.validation.core.data.{Source, SourceCodec, TypeMapper}

import scala.language.implicitConversions
import scala.reflect._

object MapCodec extends SourceCodec[ParamsMap, Option[Any]] {
  override implicit def toSource(source: ParamsMap): Source[SourceT, Contract] = new MapSource(source)

  override implicit lazy val stringMapper: TypeMapper[Contract, String] = new BasicTypeMapper
  override implicit lazy val shortMapper: TypeMapper[Contract, Short] = new BasicTypeMapper
  override implicit lazy val intMapper: TypeMapper[Contract, Int] = new BasicTypeMapper
  override implicit lazy val doubleMapper: TypeMapper[Contract, Double] = new BasicTypeMapper

  override implicit lazy val booleanMapper: TypeMapper[Contract, Boolean] = new BasicTypeMapper

  override implicit def collectionMapper[A: ClassTag]
  (implicit filedMapper: TypeMapper[Contract, A]): TypeMapper[Contract, Iterable[A]] =
    new CollectionTypeMapper[A]
}
