package pl.onewebpro.validation.basic.codec

import org.scalatest.{FlatSpec, Matchers}
import pl.onewebpro.validation.basic.codec.MapCodec._
import pl.onewebpro.validation.{of => ofV, _}

class MapCodecTest extends FlatSpec with Matchers {
  val strucutreData = Map(
    "name" -> "Alfred",
    "age" -> 18,
    "married" -> false,
    "cats" -> Iterable("mini", "patty")
  )

  case class TestEntity(name: String, age: Int, married: Boolean, cats: Iterable[String])

  val testSchema = schema(
    "name" -> nonEmptyString,
    "age" -> min(18),
    "married" -> ofV[Boolean],
    "cats" -> collection(nonEmptyString)
  )(TestEntity.apply)(TestEntity.unapply)

  "MapCodec" should "parse structure" in {
    testSchema.validate(strucutreData).isValid shouldBe true
  }
}
