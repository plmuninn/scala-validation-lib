package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.data.Formatter

import scala.reflect.ClassTag

class CollectionFormatter[T: ClassTag](implicit formatter: Formatter[Option[Json], T]) extends CirceFormatter[Iterable[T]] {
  override protected def apply(json: Json): Validation[Iterable[T]] =
    json.asArray match {
      case Some(array) => array.map(Option.apply).map(formatter.apply).swap
      case _ => error
    }
}
