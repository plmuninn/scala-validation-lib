package pl.onewebpro.validation.core

import pl.onewebpro.validation.test.UnitTest
import pl.onewebpro.validation._
import pl.onewebpro.validation.core.error.SimpleError

class IterableValidationImplicitTest extends UnitTest {

  "IterableValidationImplicit" should "handle empty list" in {
    val success = Iterable.empty[Validation[String]]
    success.swap.isValid shouldBe true
  }

  it should "handle single list" in {
    val success = Iterable(Validator.success(""))
    success.swap.isValid shouldBe true
    success.swap.toOption.get.head shouldBe ""

    val errors = Iterable(Validator.failure[String](SimpleError("error")))
    errors.swap.isValid shouldBe false
  }

  it should "list" in {
    val success = Iterable(Validator.success(""), Validator.success(""), Validator.success(""))
    success.swap.isValid shouldBe true
    success.swap.toOption.get.size shouldBe 3

    val errors = Iterable(Validator.failure[String](SimpleError("error")), Validator.failure[String](SimpleError("error")))
    errors.swap.isValid shouldBe false
  }
}
