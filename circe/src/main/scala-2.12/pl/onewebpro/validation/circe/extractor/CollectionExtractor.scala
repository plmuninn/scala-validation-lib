package pl.onewebpro.validation.circe.extractor

import io.circe.Json
import pl.onewebpro.validation.core.Validation

import scala.reflect.ClassTag


class CollectionExtractor[T: ClassTag] extends CirceExtractor[Iterable[T]] {
  override def apply(json: Json): Validation[Iterable[T]] =
    json.asArray match {
      case Some(array) => ???
      case _ => error
    }
}
