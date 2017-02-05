package pl.onewebpro.validation.entity

import pl.onewebpro.validation.validator.Validator

case class ValidationMap[T](key: String, validator: Validator[T])
