package pl.onewebpro.validation.data

import pl.onewebpro.validation.{FieldName, Validation, Validator}

/**
  * Extracts specific type from source. For example, from json it will extract string type of json
  *
  * @tparam S Source type for example json
  * @tparam R Result type we are expecting to get
  */
trait Extractor[S, R] {

  protected def error: Validation[R] = Validator.failure("error.invalid_type")

  def apply(fieldName: FieldName, value: S): Validation[R]
}
