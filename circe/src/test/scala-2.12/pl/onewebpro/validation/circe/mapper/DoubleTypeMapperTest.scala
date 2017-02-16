package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import org.scalatest.{FlatSpec, Matchers}


class DoubleTypeMapperTest extends FlatSpec with Matchers {
  "DoubleTypeMapper" should "handle valid json" in {
    val valid = Json.fromDouble(2.0)
    DoubleTypeMapper(valid).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromString("some string")
    DoubleTypeMapper(Some(invalid)).isValid shouldBe false
  }
}
