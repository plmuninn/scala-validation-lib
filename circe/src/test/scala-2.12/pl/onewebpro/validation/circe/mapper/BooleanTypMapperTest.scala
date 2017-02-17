package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import pl.onewebpro.validation.test.UnitTest


class BooleanTypMapperTest extends UnitTest {
  "BooleanTypMapper" should "handle valid json" in {
    val valid = Json.fromBoolean(true)
    BooleanTypeMapper(Some(valid)).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromString("xxx")
    BooleanTypeMapper(Some(invalid)).isValid shouldBe false
  }
}
