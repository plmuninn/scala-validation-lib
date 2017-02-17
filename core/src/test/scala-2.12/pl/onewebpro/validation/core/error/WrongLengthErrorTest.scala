package pl.onewebpro.validation.core.error

import pl.onewebpro.validation.test.UnitTest

class WrongLengthErrorTest extends UnitTest {
  "WrongLengthError" should "return proper message" in {
    val minError = WrongLengthError("age", 10, Some(5), None)
    val maxError = WrongLengthError("age", 10, None, Some(5))
    val bothErrors = WrongLengthError("age", 10, Some(5), Some(5))
    val nonErrors = WrongLengthError("age", 10, None, None)

    minError.message shouldBe "age: 10 smaller then minimum 5"
    maxError.message shouldBe "age: 10 greater then maximum 5"
    bothErrors.message shouldBe "age: 10 greater then 5 or smaller then 5"
    nonErrors.message shouldBe "age"
  }
}
