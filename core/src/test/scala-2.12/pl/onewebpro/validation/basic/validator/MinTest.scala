package pl.onewebpro.validation.basic.validator

import org.scalatest.{FlatSpec, Matchers}


class MinTest extends FlatSpec with Matchers {
  "Min" should "validate properly" in {
    val validator = new Min(10)
    validator.apply(10).isValid shouldBe true
    validator.apply(9).isValid shouldBe false
  }
}
