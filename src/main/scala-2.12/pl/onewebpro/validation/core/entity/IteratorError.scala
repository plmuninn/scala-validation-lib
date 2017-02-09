package pl.onewebpro.validation.core.entity

case class IteratorError(index: String, value: ErrorValue) extends ErrorValue {
  override def label: String = value.label

  override def message: String = value.message
}
