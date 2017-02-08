package pl.onewebpro.validation.core.entity


case class SimpleError(key: String) extends ErrorValue {
  override def message: String = key
}
