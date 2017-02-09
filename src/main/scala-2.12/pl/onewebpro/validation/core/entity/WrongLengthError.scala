package pl.onewebpro.validation.core.entity


case class WrongLengthError(label: String, got: Int, min: Option[Int], max: Option[Int]) extends ErrorValue {
  override def message: String = (min, max) match {
    case (Some(minValue), Some(maxValue)) => s"$label: $got greater then $maxValue or smaller then $minValue"
    case (None, Some(maxValue)) => s"$label: $got greater then maximum $maxValue"
    case (Some(minValue), None) => s"$label: $got smaller then minimum $minValue"
    case _ => label
  }
}
