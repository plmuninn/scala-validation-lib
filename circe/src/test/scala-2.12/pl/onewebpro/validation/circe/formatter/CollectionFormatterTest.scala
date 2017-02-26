package pl.onewebpro.validation.circe.formatter

import io.circe.Json
import pl.onewebpro.validation.test.UnitTest


class CollectionFormatterTest extends UnitTest {
  implicit val implicitValidator = StringFormatter
  val validator = new CollectionFormatter[String]()

  "CollectionFormatter" should "handle valid json" in {
    val valid = Json.fromValues(Iterable(Json.fromString("one"), Json.fromString("two")))
    validator.apply(valid).isValid shouldBe true
  }

  it should "handle invalid json" in {
    val invalid = Json.fromValues(Iterable(Json.fromBoolean(true), Json.fromBoolean(false)))
    validator.apply(invalid).isValid shouldBe false
  }
}
