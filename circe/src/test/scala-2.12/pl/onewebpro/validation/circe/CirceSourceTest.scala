package pl.onewebpro.validation.circe

import io.circe.Json
import org.scalatest.{FlatSpec, Matchers}


class CirceSourceTest extends FlatSpec with Matchers {
  "CirceSource" should "return proper value" in {
    val properJson = Json.obj(
      "someKey" -> Json.fromString("I am value!")
    )
    val source = new CirceSource(properJson)
    source.findByName("someKey").isDefined shouldBe true
    source.findByName("otherKey").isDefined shouldBe false
  }

  it should "handle non object" in {
    val wrongJson = Json.fromString("what I am doing here")
    val source = new CirceSource(wrongJson)
    source.findByName("any key").isDefined shouldBe false
  }
}
