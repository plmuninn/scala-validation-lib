package pl.onewebpro.validation.basic.validator

import pl.onewebpro.validation.test.UnitTest


class NonEmptyStringValidatorTest extends UnitTest {
  "NonEmptyStringValidator" should "validate properly" in {
    NonEmptyStringValidator.apply("something").isValid shouldBe true
    NonEmptyStringValidator.apply("").isValid shouldBe false
  }
}
