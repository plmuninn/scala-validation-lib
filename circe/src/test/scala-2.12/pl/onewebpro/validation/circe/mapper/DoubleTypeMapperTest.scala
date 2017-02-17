package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import pl.onewebpro.validation.test.UnitTest


class DoubleTypeMapperTest extends UnitTest {
  "DoubleTypeMapper" should "handle valid json" in {
    val valid = Json.fromDouble(2.0)
    DoubleTypeMapper(valid).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromString("some string")
    DoubleTypeMapper(Some(invalid)).isValid shouldBe false
  }
}
