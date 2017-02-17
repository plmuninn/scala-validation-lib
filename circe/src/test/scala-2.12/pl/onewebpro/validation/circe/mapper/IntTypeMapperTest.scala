package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import pl.onewebpro.validation.test.UnitTest

class IntTypeMapperTest extends UnitTest {
  "IntTypeMapper" should "handle valid json" in {
    val valid = Json.fromInt(1)
    IntTypeMapper(Some(valid)).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromString("some string")
    IntTypeMapper(Some(invalid)).isValid shouldBe false
  }
}
