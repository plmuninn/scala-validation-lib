package pl.onewebpro

import pl.onewebpro.basic.extractors.{IntegerExtractor, OptionalStringExtractor, StringExtractor}
import pl.onewebpro.validation.core.data.Source

import scala.language.implicitConversions

package object basic {
  type ParamsMap = Map[String, String]

  implicit def paramsToSource(source: ParamsMap): Source[ParamsMap] = new ParamsMapSource(source)

  implicit class ParamsMapImplicits(map: ParamsMap) {
    def toSource: Source[ParamsMap] = map
  }

  implicit val stringExtractor = StringExtractor
  implicit val optionalStringExtractor = OptionalStringExtractor
  implicit val integerStringExtractor = new IntegerExtractor
}
