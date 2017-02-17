package pl.onewebpro.validation.basic.validator

import pl.onewebpro.validation.test.UnitTest


class TextFieldValidatorTest extends UnitTest {
  "TextFieldValidator" should "validate properly" in {
    val validator = new TextFieldValidator(2, 10)
    validator.apply("").isValid shouldBe false
    validator.apply("12").isValid shouldBe true
    validator.apply("1234567890").isValid shouldBe true
    validator.apply("12345678901").isValid shouldBe false
  }
}
