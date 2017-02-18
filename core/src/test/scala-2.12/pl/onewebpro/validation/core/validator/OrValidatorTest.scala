package pl.onewebpro.validation.core.validator

import pl.onewebpro.validation.test.UnitTest
import pl.onewebpro.validation._

class OrValidatorTest extends UnitTest {
  "OrValidator" should "validate by on of validators" in {
    val validator = new OrValidator[Int](min(5), max(10))
    validator.apply(5).isValid shouldBe true // Because min says is ok
    validator.apply(4).isValid shouldBe true // Because max says is ok
  }
}
