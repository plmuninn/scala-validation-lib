package pl.onewebpro.validation.circe

import io.circe.Json
import org.scalatest.{FlatSpec, Matchers}
import pl.onewebpro.validation.circe.Codec._
import pl.onewebpro.validation.{of => ofV, _}

class CodecTest extends FlatSpec with Matchers {

  case class TestClass(name: String, age: Int, money: Double, isMarried: Boolean, cars: Iterable[String])

  val testSchema = schema(
    "name" -> nonEmptyString,
    "age" -> ofV[Int],
    "money" -> ofV[Double],
    "married" -> ofV[Boolean],
    "cars" -> collection(nonEmptyString)
  )(TestClass.apply)(TestClass.unapply)

  val json = Json.obj(
    "name" -> Json.fromString("name"),
    "age" -> Json.fromInt(22),
    "money" -> Json.fromDoubleOrNull(0.0),
    "married" -> Json.fromBoolean(false),
    "cars" -> Json.fromValues(Iterable(Json.fromString("Porshe")))
  )

  "Codec" should "provide all basic implicits" in {
    testSchema.validate(json).isValid shouldBe true
  }
}
