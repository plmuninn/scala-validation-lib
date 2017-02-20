package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.test.UnitTest


class DoubleFormatterTest extends UnitTest {
  "DoubleFormatter" should "handle valid json" in {
    val valid = Json.fromDouble(2.0)
    DoubleFormatter(valid).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromString("some string")
    DoubleFormatter(Some(invalid)).isValid shouldBe false
  }
}
