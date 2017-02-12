package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.data.TypeMapper

import scala.reflect.ClassTag

class CollectionTypeMapper[T: ClassTag](implicit mapper: TypeMapper[Option[Json], T]) extends CirceTypeMapper[Iterable[T]] {
  override def apply(json: Json): Validation[Iterable[T]] =
    json.asArray match {
      case Some(array) => array.map(Option.apply).map(mapper.apply).swap
      case _ => error
    }
}
