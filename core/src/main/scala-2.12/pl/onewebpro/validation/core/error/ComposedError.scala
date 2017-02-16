package pl.onewebpro.validation.core.error

/**
  * Class represents composed error for more complex error schemas. It gives way
  * to handle chained keys with errors. For example  "object" -> "list" -> 10th element -> error
  */
case class ComposedError(key: String, error: ErrorValue) extends ErrorValue {

  lazy val keys: Iterable[String] = error match {
    case e: ErrorWithKey => Iterable(key, e.key)
    case e: ComposedError => Iterable(key) ++ e.keys
    case _ => Iterable(key)
  }

  lazy val composedKey: String = keys.mkString(".")

  lazy val composedMessage: String = error match {
    case e: ComposedError => e.composedMessage
    case _ => error.message
  }


  def toTuple: (String, String) = composedKey -> error.message

  def toMap: Map[String, String] = Map(toTuple)

  override def message: String = s"$composedKey:$composedMessage"
}
