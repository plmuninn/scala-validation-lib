package pl.onewebpro.validation.core.entity

trait ErrorValue {
  def label: String

  def message: String = label
}
