package pl.onewebpro.validation.core.entity

trait ErrorValue {
  def key: String

  def expected[T]: Option[T] = None

  def got[T]: Option[T] = None

  def message: String
}
