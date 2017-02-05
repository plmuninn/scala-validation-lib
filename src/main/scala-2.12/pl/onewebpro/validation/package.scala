package pl.onewebpro

import cats.data.ValidatedNel
import pl.onewebpro.validation.entity.ValidationError

package object validation {
  type FieldName = String
  type Validation[T] = ValidatedNel[String, T]
  type Validated[T] = ValidatedNel[ValidationError, T]
}
