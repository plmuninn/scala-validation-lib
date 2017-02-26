package pl.onewebpro.validation.core.data

import pl.onewebpro.validation.core.entity.ValidationMap
import pl.onewebpro.validation.core.{FieldName, Validation, Validator}
import pl.onewebpro.validation.core.error._

/**
  * Represents source of data like json, xml file etc.
  *
  * @tparam T Type of source, like json or xml
  * @tparam V Agreed type of value for formatter
  */
trait Source[T, V] {
  def source: T

  def findByName(fieldName: FieldName): Option[V]

  /**
    * Extracts data from source by name, for example we want to get string
    */
  def extract[A](fieldName: FieldName)(implicit formatter: Formatter[V, A]): Validation[A] =
    formatter match {
      case opt: OptionalFormatter[V, A] => opt.apply(findByName(fieldName)).asInstanceOf[Validation[A]]
      case _ => findByName(fieldName).map(formatter.apply).getOrElse(Validator.failure(fieldNotFound))
    }

  /**
    * Validate value using validation map
    */
  def validate[A](map: ValidationMap[A])(implicit formatter: Formatter[V, A]): Validation[A] =
    map.validate[T, V](this)
}
