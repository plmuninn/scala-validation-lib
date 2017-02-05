package pl.onewebpro

import cats.data.ValidatedNel
import pl.onewebpro.validation.entity.{ValidationError => Error}

package object validation {
  type FieldName = String
  type ValidationError = String
  type Validation[T] = ValidatedNel[ValidationError, T]
  type Validated[T] = ValidatedNel[Error, T]
}
