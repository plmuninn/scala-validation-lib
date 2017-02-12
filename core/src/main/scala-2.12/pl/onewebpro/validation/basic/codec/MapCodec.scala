package pl.onewebpro.validation.basic.codec

import pl.onewebpro.validation.basic.codec.mapper.{CollectionTypeTypeMapper, MapSource, TypeTypeMapper}
import pl.onewebpro.validation.core.data.{Source, SourceCodec, TypeMapper}

import scala.language.implicitConversions
import scala.reflect._

object MapCodec extends SourceCodec[ParamsMap, Option[Any]] {
  override implicit def toSource(source: ParamsMap): Source[ParamsMap, Option[Any]] = new MapSource(source)

  override implicit lazy val stringMapper: TypeMapper[Option[Any], String] = new TypeTypeMapper
  override implicit lazy val shortMapper: TypeMapper[Option[Any], Short] = new TypeTypeMapper
  override implicit lazy val intMapper: TypeMapper[Option[Any], Int] = new TypeTypeMapper
  override implicit lazy val doubleMapper: TypeMapper[Option[Any], Double] = new TypeTypeMapper

  override implicit lazy val booleanMapper: TypeMapper[Option[Any], Boolean] = new TypeTypeMapper

  override implicit def collectionMapper[A: ClassTag]
  (implicit filedMapper: TypeMapper[Option[Any], A]): TypeMapper[Option[Any], Iterable[A]] =
    new CollectionTypeTypeMapper[A]
}
