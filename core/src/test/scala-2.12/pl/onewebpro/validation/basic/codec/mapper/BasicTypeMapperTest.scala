package pl.onewebpro.validation.basic.codec.mapper

import org.scalatest.{FlatSpec, Matchers}


class BasicTypeMapperTest extends FlatSpec with Matchers {
  "BasicTypeMapper" should "map simple type" in {
    val stringMapper = new BasicTypeMapper[String]()
    stringMapper.apply(Some("")).isValid shouldBe true
    stringMapper.apply(Some(1)).isValid shouldBe false
  }
}
