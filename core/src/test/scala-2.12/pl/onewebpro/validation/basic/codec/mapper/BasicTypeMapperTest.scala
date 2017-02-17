package pl.onewebpro.validation.basic.codec.mapper

import pl.onewebpro.validation.test.UnitTest


class BasicTypeMapperTest extends UnitTest {
  "BasicTypeMapper" should "map simple type" in {
    val stringMapper = new BasicTypeMapper[String]()
    stringMapper.apply(Some("")).isValid shouldBe true
    stringMapper.apply(Some(1)).isValid shouldBe false
  }
}
