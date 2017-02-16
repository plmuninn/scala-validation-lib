package pl.onewebpro.validation.basic.codec

import pl.onewebpro.validation.basic.codec.mapper.{CollectionTypeTypeMapper, MapSource, TypeTypeMapper}
import pl.onewebpro.validation.core.data.{Source, SourceCodec, TypeMapper}

import scala.language.implicitConversions
import scala.reflect._

object MapCodec extends SourceCodec[ParamsMap, Option[Any]] {
  override implicit def toSource(source: ParamsMap): Source[SourceT, Contract] = new MapSource(source)

  override implicit lazy val stringMapper: TypeMapper[Contract, String] = new TypeTypeMapper
  override implicit lazy val shortMapper: TypeMapper[Contract, Short] = new TypeTypeMapper
  override implicit lazy val intMapper: TypeMapper[Contract, Int] = new TypeTypeMapper
  override implicit lazy val doubleMapper: TypeMapper[Contract, Double] = new TypeTypeMapper

  override implicit lazy val booleanMapper: TypeMapper[Contract, Boolean] = new TypeTypeMapper

  override implicit def collectionMapper[A: ClassTag]
  (implicit filedMapper: TypeMapper[Contract, A]): TypeMapper[Contract, Iterable[A]] =
    new CollectionTypeTypeMapper[A]
}
