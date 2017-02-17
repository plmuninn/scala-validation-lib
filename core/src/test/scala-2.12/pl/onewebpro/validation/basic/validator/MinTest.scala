package pl.onewebpro.validation.basic.validator

import pl.onewebpro.validation.test.UnitTest


class MinTest extends UnitTest {
  "Min" should "validate properly" in {
    val validator = new Min(10)
    validator.apply(10).isValid shouldBe true
    validator.apply(9).isValid shouldBe false
  }
}
