package pl.onewebpro.validation.core.entity

case class ValidationError(key: String, error: ErrorValue) {

  val composedKey: String = error match {
    case e: IteratorError => s"$key.${e.index}"
    case _ => key
  }

  override def toString: String =
    s"""ValidationError($composedKey -> ${error.message})"""

  def toTuple: (String, String) = composedKey -> error.message

  def toMap: Map[String, String] = Map(toTuple)
}
