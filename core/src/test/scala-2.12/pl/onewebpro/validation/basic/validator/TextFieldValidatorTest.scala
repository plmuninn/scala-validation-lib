package pl.onewebpro.validation.basic.validator

import org.scalatest.{FlatSpec, Matchers}


class TextFieldValidatorTest extends FlatSpec with Matchers {
  "TextFieldValidator" should "validate properly" in {
    val validator = new TextFieldValidator(2, 10)
    validator.apply("").isValid shouldBe false
    validator.apply("12").isValid shouldBe true
    validator.apply("1234567890").isValid shouldBe true
    validator.apply("12345678901").isValid shouldBe false
  }
}
