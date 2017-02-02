package pl.onewebpro

import pl.onewebpro.basic.extractors.{IntegerExtractor, OptionalStringExtractor, StringExtractor}
import pl.onewebpro.validation.data.Source


package object basic {
  type ParamsMap = Map[String, String]

  implicit class ParamsMapImplicits(map: ParamsMap) {
    def toSource: Source[ParamsMap] = new ParamsMapSource(map)
  }

  implicit val stringExtractor = StringExtractor
  implicit val optionalStringExtractor = OptionalStringExtractor
  implicit val integerStringExtractor = new IntegerExtractor
}
