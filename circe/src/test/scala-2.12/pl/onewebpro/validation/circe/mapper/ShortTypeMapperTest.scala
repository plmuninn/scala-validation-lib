package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import org.scalatest.{FlatSpec, Matchers}


class ShortTypeMapperTest extends FlatSpec with Matchers {
  "ShortTypeMapper" should "handle valid json" in {
    val valid = Json.fromInt(1)
    ShortTypeMapper(Some(valid)).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromString("some string")
    ShortTypeMapper(Some(invalid)).isValid shouldBe false
  }
}
