package pl.onewebpro.validation.core.data

import pl.onewebpro.validation.core.entity.ValidationMap
import pl.onewebpro.validation.core.{FieldName, Validation}

/**
  * Represents source of data like json, xml file etc.
  *
  * @tparam T Type of source, like json or xml
  * @tparam V Agreed type of value for formatter
  */
trait Source[T, V] {
  def source: T

  def findByName(fieldName: FieldName): V

  /**
    * Extracts data from source by name, for example we want to get string
    */
  def extract[A](fieldName: FieldName)(implicit formatter: Formatter[V, A]): Validation[A] =
    formatter.apply(findByName(fieldName))

  /**
    * Validate value using validation map
    */
  def validate[A](map: ValidationMap[A])(implicit formatter: Formatter[V, A]): Validation[A] =
    map.validate[T, V](this)
}
