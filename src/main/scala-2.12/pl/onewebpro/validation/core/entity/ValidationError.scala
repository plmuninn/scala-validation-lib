package pl.onewebpro.validation.core.entity

case class ValidationError(key: String, error: ErrorValue) {
  override def toString: String =
    s"""ValidationError($key -> ${error.message})"""
}
