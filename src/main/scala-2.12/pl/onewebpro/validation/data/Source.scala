package pl.onewebpro.validation.data

import pl.onewebpro.validation.{FieldName, Validation}

/**
  * Represents source of data like json, xml file etc.
  *
  * @tparam T Type of source, like json or xml
  */
trait Source[T] {

  /**
    * Source of data, for example json
    *
    * @return
    */
  def source: T

  /**
    * Extracts data from source by name, for example we want to get string
    *
    * @return
    */
  def extract[A](fieldName: FieldName)(implicit extractor: Extractor[T, A]): Validation[A] =
    extractor(fieldName, source)
}
