package pl.onewebpro.validation.basic.validator

import org.scalatest.{FlatSpec, Matchers}


class MaxTest extends FlatSpec with Matchers {
  "Max" should "validate properly" in {
    val validator = new Max(10)
    validator.apply(10).isValid shouldBe true
    validator.apply(11).isValid shouldBe false
  }
}
