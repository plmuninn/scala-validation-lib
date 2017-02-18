package pl.onewebpro.validation.core.validator

import pl.onewebpro.validation.core.error.emptyStringError
import pl.onewebpro.validation.nonEmptyString
import pl.onewebpro.validation.test.UnitTest


class NonEmptyCollectionValidatorTest extends UnitTest {
  "NonEmptyCollectionValidator" should "return proper errors" in {
    val validator = new NonEmptyCollectionValidator[String](nonEmptyString)
    validator.apply(Iterable()).isValid shouldBe false
    validator.apply(Iterable("whats up")).isValid shouldBe true
    validator.apply(Iterable("whats up", "")).isValid shouldBe false
    val errors = validator.apply(Iterable("whats up", "")).toEither.left.get
    errors.head.message shouldBe s"1:${emptyStringError.message}"
  }
}
