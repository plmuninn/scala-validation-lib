package pl.onewebpro.validation.circe

import io.circe.Json
import pl.onewebpro.validation.circe.mapper._
import pl.onewebpro.validation.core.data.{Source, SourceCodec, TypeMapper}

import scala.language.implicitConversions
import scala.reflect.ClassTag

object Codec extends SourceCodec[Json, Option[Json]] {

  override implicit def toSource(source: Json): Source[SourceT, Contract] = new CirceSource(source)

  override implicit val stringMapper: TypeMapper[Contract, String] = StringTypeMapper

  override implicit val shortMapper: TypeMapper[Contract, Short] = ShortTypeMapper

  override implicit val intMapper: TypeMapper[Contract, Int] = IntTypeMapper

  override implicit val doubleMapper: TypeMapper[Contract, Double] = DoubleTypeMapper

  override implicit val booleanMapper: TypeMapper[Contract, Boolean] = BooleanTypeMapper

  override implicit def collectionMapper[A: ClassTag]
  (implicit filedMapper: TypeMapper[Contract, A]): TypeMapper[Contract, Iterable[A]] =
    new CollectionTypeMapper
}
