package pl.onewebpro.basic.extractors

import pl.onewebpro.basic.ParamsMap
import pl.onewebpro.validation.{Validation, Validator}
import pl.onewebpro.validation.data.Extractor

import scala.util.{Failure, Success, Try}


class IntegerExtractor(implicit stringExtractor: Extractor[ParamsMap, String]) extends Extractor[ParamsMap, Int] {

  private def asInt(field: String): Validation[Int] =
    Try(field.toInt) match {
      case Success(intValue) => Validator.success(intValue)
      case Failure(_) => error
    }

  override def apply(fieldName: String, value: ParamsMap): Validation[Int] =
    stringExtractor(fieldName, value) andThen asInt
}
