import cats.data.NonEmptyList
import pl.onewebpro.validation.core.error.{ComposedError, SimpleError}

val error = SimpleError("simple_error")
val iteratorError = ComposedError("0", error)
val fieldError = ComposedError("names", iteratorError)
val packageError = ComposedError("my.package", fieldError)
packageError.composedKey
packageError.message

val errors = NonEmptyList.of(packageError)
errors.groupedByKey
