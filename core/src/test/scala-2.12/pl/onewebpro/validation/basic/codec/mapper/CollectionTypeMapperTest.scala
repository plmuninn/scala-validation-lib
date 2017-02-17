package pl.onewebpro.validation.basic.codec.mapper

import pl.onewebpro.validation.test.UnitTest


class CollectionTypeMapperTest extends UnitTest {
  implicit val stringMapper = new BasicTypeMapper[String]()
  val mapper = new CollectionTypeMapper[String]()

  "CollectionTypeMapper" should "map properly" in {
    mapper.apply(Some(List(""))).isValid shouldBe true
    mapper.apply(Some(List(1))).isValid shouldBe false
    mapper.apply(Some("")).isValid shouldBe false
  }
}
