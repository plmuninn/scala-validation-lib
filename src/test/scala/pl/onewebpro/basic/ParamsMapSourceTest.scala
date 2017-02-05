package pl.onewebpro.basic

import org.scalatest.{FlatSpec, Matchers}
import pl.onewebpro.validation.data.Source
import pl.onewebpro.validation.schema.Schema


class ParamsMapSourceTest extends FlatSpec with Matchers {

  val map: ParamsMap = Map(
    "firstKey" -> "value",
    "integerType" -> "1"
  )

  val mapSource: Source[ParamsMap] = map.toSource

  "ParamsMapSource" should "extract string" in {
    val firstKeyValidation = mapSource.extract[String]("firstKey")

    firstKeyValidation.isValid shouldBe true
    firstKeyValidation.toOption.get shouldBe "value"
  }

  "ParamsMapSource" should "fail extracting string" in {
    val firstKeyValidation = mapSource.extract[String]("oh my")

    firstKeyValidation.isValid shouldBe false
    firstKeyValidation.toOption shouldBe None
  }

  "ParamsMapSource" should "extract optional value" in {
    val optionalValueValidation = mapSource.extract[Option[String]]("something I made up")

    optionalValueValidation.isValid shouldBe true
    optionalValueValidation.toOption.get shouldBe None
  }

  "ParamsMapSource" should "extract integer value" in {
    val optionalValueValidation = mapSource.extract[Int]("integerType")

    optionalValueValidation.isValid shouldBe true
    optionalValueValidation.toOption.get shouldBe 1
  }

  "OptionalValidator" should "validate" in {
    case class MyClass(name: String, lastName: Iterable[String])

    val schema = Schema(
      "name" -> nonEmptyString,
      "lastName" -> collection(nonEmptyString)
    )(MyClass.apply)(MyClass.unapply)

    schema.validate(map).isValid shouldBe true

  }
}
