package pl.onewebpro.validation.core.error

import pl.onewebpro.validation.test.UnitTest

class ComposedErrorTest extends UnitTest {
  "ComposedError" should "compose key properly" in {
    val outError = SimpleError("WRONG")
    val indexError = ComposedError("0", outError)
    val fieldError = ComposedError("cats", indexError)
    fieldError.composedKey shouldBe "cats.0"
    fieldError.message shouldBe "cats.0:WRONG"
  }
}
