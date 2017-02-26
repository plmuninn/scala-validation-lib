package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.test.UnitTest


class BooleanFormatterTest extends UnitTest {
  "BooleanFormatter" should "handle valid json" in {
    val valid = Json.fromBoolean(true)
    BooleanFormatter(valid).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromString("xxx")
    BooleanFormatter(invalid).isValid shouldBe false
  }
}
