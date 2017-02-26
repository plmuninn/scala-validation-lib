package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.test.UnitTest

class StringFormatterTest extends UnitTest {
  "StringFormatter" should "handle valid json" in {
    val valid = Json.fromString("some legit string")
    StringFormatter(valid).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromValues(Iterable(Json.fromBoolean(true)))
    StringFormatter(invalid).isValid shouldBe false
  }
}
