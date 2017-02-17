package pl.onewebpro.validation.circe.mapper

import io.circe.Json
import pl.onewebpro.validation.test.UnitTest


class CollectionTypeMapperTest extends UnitTest {
  implicit val implicitValidator = StringTypeMapper
  val validator = new CollectionTypeMapper[String]()

  "CollectionTypeMapper" should "handle valid json" in {
    val valid = Json.fromValues(Iterable(Json.fromString("one"), Json.fromString("two")))
    validator.apply(Some(valid)).isValid shouldBe true
  }

  "CollectionTypeMapper" should "handle invalid json" in {
    val invalid = Json.fromValues(Iterable(Json.fromBoolean(true), Json.fromBoolean(false)))
    validator.apply(Some(invalid)).isValid shouldBe false
  }
}
