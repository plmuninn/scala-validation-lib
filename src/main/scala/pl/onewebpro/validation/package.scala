package pl.onewebpro

import cats.data.ValidatedNel
import pl.onewebpro.validation.entity.ValidationError

package object validation {
  type Validation[T] = ValidatedNel[ValidationError, T]
}
