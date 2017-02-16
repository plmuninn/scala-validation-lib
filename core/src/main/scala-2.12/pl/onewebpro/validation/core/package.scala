package pl.onewebpro.validation

import cats.data.ValidatedNel
import pl.onewebpro.validation.core.error.{ErrorValue, ComposedError}

package object core {
  type FieldName = String
  // Single validation result
  type Validation[T] = ValidatedNel[ErrorValue, T]
  // Whole validation process result
  type Validated[T] = ValidatedNel[ComposedError, T]
}
