package pl.onewebpro.validation.basic.codec.formatter

import pl.onewebpro.validation.test.UnitTest


class CollectionTypeFormatterTest extends UnitTest {
  implicit val stringFormatter = new TypeFormatter[String]()
  val formatter = new CollectionTypeFormatter[String]()

  "CollectionTypeFormatter" should "map properly" in {
    formatter.apply(List("")).isValid shouldBe true
    formatter.apply(List(1)).isValid shouldBe false
    formatter.apply("").isValid shouldBe false
  }
}
