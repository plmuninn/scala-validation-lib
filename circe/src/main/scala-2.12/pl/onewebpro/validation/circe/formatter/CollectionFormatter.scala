package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.core.Validation
import pl.onewebpro.validation.core.data.Formatter

import scala.reflect.ClassTag

class CollectionFormatter[T: ClassTag](implicit formatter: Formatter[Json, T]) extends Formatter[Json, Iterable[T]] {
  override def apply(json: Json): Validation[Iterable[T]] =
    json.asArray match {
      case Some(array) => array.map(formatter.apply).swap
      case _ => error
    }
}
