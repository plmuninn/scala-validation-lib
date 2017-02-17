package pl.onewebpro.validation.basic.codec.mapper

import org.scalatest.{FlatSpec, Matchers}


class CollectionTypeMapperTest extends FlatSpec with Matchers {
  implicit val stringMapper = new BasicTypeMapper[String]()
  val mapper = new CollectionTypeMapper[String]()

  "CollectionTypeMapper" should "map properly" in {
    mapper.apply(Some(List(""))).isValid shouldBe true
    mapper.apply(Some(List(1))).isValid shouldBe false
    mapper.apply(Some("")).isValid shouldBe false
  }
}
