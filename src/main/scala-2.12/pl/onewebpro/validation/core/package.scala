package pl.onewebpro.validation

import cats.data.ValidatedNel
import pl.onewebpro.validation.core.entity.ValidationError

package object core {
  type FieldName = String
  type Validation[T] = ValidatedNel[String, T]
  type Validated[T] = ValidatedNel[ValidationError, T]
}