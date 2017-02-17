package pl.onewebpro.validation.basic.codec

import pl.onewebpro.validation.test.UnitTest

class MapSourceTest extends UnitTest {
  val sourceData = Map(
    "key" -> "value"
  )
  val source = new MapSource(sourceData)

  "MapSource" should "return proper value" in {
    source.findByName("key").isDefined shouldBe true
    source.findByName("fake").isDefined shouldBe false
  }
}
