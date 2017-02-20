package pl.onewebpro.validation.basic.codec.formatter

import pl.onewebpro.validation.test.UnitTest


class TypeFormatterTest extends UnitTest {
  "TypeFormatter" should "map simple type" in {
    val stringFormatter = new TypeFormatter[String]()
    stringFormatter.apply(Some("")).isValid shouldBe true
    stringFormatter.apply(Some(1)).isValid shouldBe false
  }
}
