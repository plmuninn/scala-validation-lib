package pl.onewebpro.validation.basic.codec

import pl.onewebpro.validation.basic.codec.MapCodec._
import pl.onewebpro.validation.test.UnitTest
import pl.onewebpro.validation.{of => ofV, _}

class MapCodecTest extends UnitTest {
  val strucutreData = Map(
    "name" -> "Alfred",
    "age" -> 18,
    "married" -> false,
    "cats" -> Iterable("mini", "patty"),
    "car" -> Some("porsche")
  )

  case class TestEntity(name: String, age: Int, married: Boolean, cats: Iterable[String], car: Option[String])

  val testSchema = schema(
    "name" -> nonEmptyString,
    "age" -> min(18),
    "married" -> ofV[Boolean],
    "cats" -> collection(nonEmptyString),
    "car" -> optional(ofV[String])
  )(TestEntity.apply)(TestEntity.unapply)

  "MapCodec" should "parse structure" in {
    val result = testSchema.validate(strucutreData)
    result.isValid shouldBe true
    val testEntity = result.toOption.get
    testEntity.name shouldBe "Alfred"
    testEntity.age shouldBe 18
    testEntity.married shouldBe false
    testEntity.cats shouldBe Iterable("mini", "patty")
    testEntity.car shouldBe Some("porsche")
  }
}
