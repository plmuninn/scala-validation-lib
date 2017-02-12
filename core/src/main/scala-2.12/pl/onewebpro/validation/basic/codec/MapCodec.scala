package pl.onewebpro.validation.basic.codec

import pl.onewebpro.validation.basic.codec.extractor.{CollectionTypeTypeMapper, MapSource, TypeTypeMapper}
import pl.onewebpro.validation.core.data.{Source, SourceCodec, TypeMapper}

import scala.language.implicitConversions
import scala.reflect._

object MapCodec extends SourceCodec[ParamsMap, Option[Any]] {
  override implicit def toSource(source: ParamsMap): Source[ParamsMap, Option[Any]] = new MapSource(source)

  override implicit lazy val stringExtractor: TypeMapper[Option[Any], String] = new TypeTypeMapper
  override implicit lazy val shortExtractor: TypeMapper[Option[Any], Short] = new TypeTypeMapper
  override implicit lazy val intExtractor: TypeMapper[Option[Any], Int] = new TypeTypeMapper
  override implicit lazy val doubleExtractor: TypeMapper[Option[Any], Double] = new TypeTypeMapper

  override implicit lazy val booleanExtractor: TypeMapper[Option[Any], Boolean] = new TypeTypeMapper

  override implicit def collectionExtractor[A: ClassTag]
  (implicit filedMapper: TypeMapper[Option[Any], A]): TypeMapper[Option[Any], Iterable[A]] =
    new CollectionTypeTypeMapper[A]
}
