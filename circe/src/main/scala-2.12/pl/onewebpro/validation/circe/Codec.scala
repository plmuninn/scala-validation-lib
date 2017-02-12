package pl.onewebpro.validation.circe

import io.circe.Json
import pl.onewebpro.validation.circe.extractor._
import pl.onewebpro.validation.core.data.{Source, SourceCodec, TypeMapper}

import scala.reflect.ClassTag

object Codec extends SourceCodec[Json, Option[Json]] {

  override implicit def toSource(source: Json): Source[Json, Option[Json]] = new CirceSource(source)

  override implicit val stringExtractor: TypeMapper[Option[Json], String] = StringTypeMapper

  override implicit val shortExtractor: TypeMapper[Option[Json], Short] = ShortTypeMapper

  override implicit val intExtractor: TypeMapper[Option[Json], Int] = IntTypeMapper

  override implicit val doubleExtractor: TypeMapper[Option[Json], Double] = DoubleTypeMapper

  override implicit val booleanExtractor: TypeMapper[Option[Json], Boolean] = BooleanTypeMapper

  override implicit def collectionExtractor[A: ClassTag]
  (implicit filedMapper: TypeMapper[Option[Json], A]): TypeMapper[Option[Json], Iterable[A]] =
    new CollectionTypeMapper
}
