package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import org.scalatest.{FlatSpec, Matchers}


class StringTypeMapperTest extends FlatSpec with Matchers {
  "StringTypeMapper" should "handle valid json" in {
    val valid = Json.fromString("some legit string")
    StringTypeMapper(Some(valid)).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromValues(Iterable(Json.fromBoolean(true)))
    StringTypeMapper(Some(invalid)).isValid shouldBe false
  }
}
