package pl.onewebpro.validation.basic.codec

import org.scalatest.{FlatSpec, Matchers}

class MapSourceTest extends FlatSpec with Matchers {
  val sourceData = Map(
    "key" -> "value"
  )
  val source = new MapSource(sourceData)

  "MapSource" should "return proper value" in {
    source.findByName("key").isDefined shouldBe true
    source.findByName("fake").isDefined shouldBe false
  }
}
