package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.test.UnitTest

class IntFormatterTest extends UnitTest {
  "IntFormatter" should "handle valid json" in {
    val valid = Json.fromInt(1)
    IntFormatter(Some(valid)).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromString("some string")
    IntFormatter(Some(invalid)).isValid shouldBe false
  }
}
