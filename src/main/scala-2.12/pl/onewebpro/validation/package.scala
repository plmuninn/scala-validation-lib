package pl.onewebpro

import cats.data.ValidatedNel

package object validation {
  type FieldName = String
  type ValidationError = String
  type Validation[T] = ValidatedNel[ValidationError, T]
}
