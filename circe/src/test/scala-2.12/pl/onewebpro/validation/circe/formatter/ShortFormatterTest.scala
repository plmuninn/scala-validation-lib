package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.test.UnitTest

class ShortFormatterTest extends UnitTest {
  "ShortFormatter" should "handle valid json" in {
    val valid = Json.fromInt(1)
    ShortFormatter(Some(valid)).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromString("some string")
    ShortFormatter(Some(invalid)).isValid shouldBe false
  }
}
