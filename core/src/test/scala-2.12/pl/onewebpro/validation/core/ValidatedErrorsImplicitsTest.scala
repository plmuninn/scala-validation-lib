package pl.onewebpro.validation.core

import cats.data.NonEmptyList
import pl.onewebpro.validation.core.error.{ComposedError, SimpleError}
import pl.onewebpro.validation.test.UnitTest
import pl.onewebpro.validation._

class ValidatedErrorsImplicitsTest extends UnitTest {
  "ValidatedErrorsImplicits" should "group by key" in {
    val errors: NonEmptyList[ComposedError] = NonEmptyList.fromList(
      List(
        ComposedError("funny", SimpleError("Yo, errors")),
        ComposedError("lazy", SimpleError("Not now")),
        ComposedError("funny", SimpleError("cmn men, we need to do something")),
        ComposedError("funny", SimpleError("nooow!")),
        ComposedError("intelligent", SimpleError("Why now?")),
        ComposedError("lazy", SimpleError("This smart guy again"))
      )
    ).get

    errors.groupedByKey.get("funny").isDefined shouldBe true
    errors.groupedByKey.get("lazy").isDefined shouldBe true
    errors.groupedByKey.get("intelligent").isDefined shouldBe true

    errors.groupedByKey("funny").toList.length shouldBe 3
    errors.groupedByKey("lazy").toList.length shouldBe 2
    errors.groupedByKey("intelligent").toList.length shouldBe 1
  }
}
