package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import pl.onewebpro.validation.test.UnitTest

class ShortTypeMapperTest extends UnitTest {
  "ShortTypeMapper" should "handle valid json" in {
    val valid = Json.fromInt(1)
    ShortTypeMapper(Some(valid)).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromString("some string")
    ShortTypeMapper(Some(invalid)).isValid shouldBe false
  }
}
