package pl.onewebpro.validation.basic.validator

import pl.onewebpro.validation.test.UnitTest


class MaxTest extends UnitTest {
  "Max" should "validate properly" in {
    val validator = new Max(10)
    validator.apply(10).isValid shouldBe true
    validator.apply(11).isValid shouldBe false
  }
}
