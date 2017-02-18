package pl.onewebpro.validation.core.validator

import pl.onewebpro.validation.test.UnitTest
import pl.onewebpro.validation._


class MultiValidatorTest extends UnitTest {
  "MultiValidator" should "valid by all validators" in {
    val validator = new MultiValidator[Int](Iterable(min(1), max(10), min(5)))
    validator.apply(5).isValid shouldBe true //This is correct
    validator.apply(0).isValid shouldBe false // This is incorrect by first validator
    validator.apply(11).isValid shouldBe false // This is incorrect by second validator
    validator.apply(4).isValid shouldBe false //This is incorrect by third validator
  }
}
