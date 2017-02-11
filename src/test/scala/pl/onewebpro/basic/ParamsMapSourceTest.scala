package pl.onewebpro.basic

import org.scalatest.{FlatSpec, Matchers}
import pl.onewebpro.validation.basic.codec.MapCodec._
import pl.onewebpro.validation._

class ParamsMapSourceTest extends FlatSpec with Matchers {

  val map: Map[String, Any] = Map(
    "firstKey" -> "value",
    "integerType" -> 1,
    "list" -> List(1, 1)
  )

  "ParamsMapSource" should "extract string" in {
    val firstKeyValidation = map.extract[String]("firstKey")

    firstKeyValidation.isValid shouldBe true
    firstKeyValidation.toOption.get shouldBe "value"
  }

  "ParamsMapSource" should "fail extracting string" in {
    val firstKeyValidation = map.extract[String]("oh my")

    firstKeyValidation.isValid shouldBe false
    firstKeyValidation.toOption shouldBe None
  }

  "ParamsMapSource" should "extract optional value" in {
    val optionalValueValidation = map.extract[Option[String]]("something I made up")

    optionalValueValidation.isValid shouldBe true
    optionalValueValidation.toOption.get shouldBe None
  }

  "ParamsMapSource" should "extract integer value" in {
    val optionalValueValidation = map.extract[Int]("integerType")

    optionalValueValidation.isValid shouldBe true
    optionalValueValidation.toOption.get shouldBe 1
  }

  "ParamsMapSource" should "extract list of integers" in {
    val optionalValueValidation = map.extract[Iterable[Int]]("list")

    optionalValueValidation.isValid shouldBe true
    optionalValueValidation.toOption.get shouldBe List(1, 1)
  }

  "OptionalValidator" should "validate" in {
    case class MyClass(name: Option[String], lastName: String)

    val result =
      schema(
        "name" -> optional(nonEmptyString),
        "lastName" -> multi(nonEmptyString, nonEmptyString)
      )(MyClass.apply)(MyClass.unapply)
        .validate(map)

    result.isValid shouldBe false
  }
}
