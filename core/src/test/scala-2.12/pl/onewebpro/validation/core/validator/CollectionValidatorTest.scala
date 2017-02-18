package pl.onewebpro.validation.core.validator

import pl.onewebpro.validation.test.UnitTest
import pl.onewebpro.validation._
import pl.onewebpro.validation.core.error._


class CollectionValidatorTest extends UnitTest {
  "CollectionValidator" should "return proper errors" in {
    val validator = new CollectionValidator[String](nonEmptyString)
    validator.apply(Iterable()).isValid shouldBe true
    validator.apply(Iterable("whats up")).isValid shouldBe true
    validator.apply(Iterable("whats up", "")).isValid shouldBe false
    val errors = validator.apply(Iterable("whats up", "")).toEither.left.get
    errors.head.message shouldBe s"1:${emptyStringError.message}"
  }
}
