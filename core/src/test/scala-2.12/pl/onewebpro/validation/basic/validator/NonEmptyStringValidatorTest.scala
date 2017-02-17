package pl.onewebpro.validation.basic.validator

import org.scalatest.{FlatSpec, Matchers}


class NonEmptyStringValidatorTest extends FlatSpec with Matchers {
  "NonEmptyStringValidator" should "validate properly" in {
    NonEmptyStringValidator.apply("something").isValid shouldBe true
    NonEmptyStringValidator.apply("").isValid shouldBe false
  }
}
