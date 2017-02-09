package pl.onewebpro.validation.core


package object error {

  trait ErrorValue {
    def message: String
  }

  case class SimpleError(message: String) extends ErrorValue

  case class ErrorWithKey(key: String, message: String) extends ErrorValue
}
