package pl.onewebpro.validation.entity

import pl.onewebpro.validation.{ValidationError => Error}

case class ValidationError(key: String, error: Error)
