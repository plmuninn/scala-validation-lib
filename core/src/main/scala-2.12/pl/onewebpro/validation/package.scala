package pl.onewebpro

import cats.data.{NonEmptyList, Validated => CatsValidated}
import cats.kernel.Semigroup
import pl.onewebpro.validation.basic.validator.{Max, Min, NonEmptyStringValidator, TextFieldValidator}
import pl.onewebpro.validation.core.data.{OptionalTypeMapper, TypeMapper}
import pl.onewebpro.validation.core.entity.{FieldMap, ValidationMap}
import pl.onewebpro.validation.core.error.ComposedError
import pl.onewebpro.validation.core.schema.Schema
import pl.onewebpro.validation.core.validator._
import pl.onewebpro.validation.core.{Validated, Validation, Validator}

import scala.collection.mutable.{Map => MutableMap}
import scala.language.implicitConversions

package object validation {

  lazy val schema = Schema

  lazy val nonEmptyString = NonEmptyStringValidator

  def textValidator(min: Int = 0, max: Int = 0): TextFieldValidator = new TextFieldValidator(min, max)

  lazy val textValidator: TextFieldValidator = textValidator()

  def optional[T](validator: Validator[T]): OptionalValidator[T] = new OptionalValidator(validator)

  def collection[T](validator: Validator[T]): CollectionValidator[T] = new CollectionValidator(validator)

  def min(min: Int): Min = new Min(min)

  def max(max: Int): Max = new Max(max)

  def minAndMax(minValue: Int, maxValue: Int): MultiValidator[Int] = multi(min(minValue), max(maxValue))

  def nonEmptyCollection[T](validator: Validator[T]): NonEmptyCollectionValidator[T] =
    new NonEmptyCollectionValidator(validator)

  def multi[T](validators: Validator[T]*): MultiValidator[T] = new MultiValidator[T](validators)

  def of[T]: TypeValidator[T] = new TypeValidator[T]

  // Map "key" -> validator to FieldMap
  implicit def pairToMap[T](pair: (String, Validator[T])): ValidationMap[T] = {
    val (key, validator) = pair
    FieldMap(key, validator)
  }

  // Implicitly resolve optional values
  implicit def optionalMapper[S, R](implicit tm: TypeMapper[S, R]): TypeMapper[S, Option[R]] =
    new OptionalTypeMapper(tm)

  /**
    * Implicits for validation result errors
    */
  implicit class ValidatedErrorsImplicits(errors: NonEmptyList[ComposedError]) {
    /**
      * We can group errors by key. Tha mean, if our field had many validators and all of them returned some error. We
      * can group this errors using field name.
      */
    def groupedByKey: Map[String, NonEmptyList[ComposedError]] = {
      type Reducer = MutableMap[String, NonEmptyList[ComposedError]]

      def foldToMutableMap: (Reducer, ComposedError) => Reducer = {
        case (container, error) =>
          val value: NonEmptyList[ComposedError] =
            if (container.contains(error.key)) container(error.key).::(error) else NonEmptyList.of(error)
          container += (error.key -> value)
      }

      errors.foldLeft[Reducer](MutableMap.empty)(foldToMutableMap).toMap
    }
  }

  /**
    * Implicits for validation result
    */
  implicit class ValidatedImplicits[T](result: Validated[T]) {
    /**
      * Provides same grouping like  ValidatedErrorsImplicits.groupedByKey but before we are resolving result.
      */
    def groupedErrors: CatsValidated[Map[String, NonEmptyList[ComposedError]], T] = {
      result.leftMap(_.groupedByKey)
    }
  }

  /**
    * Implicits for validation collections
    */
  implicit class IterableValidationImplicit[T](values: Iterable[Validation[T]]) {

    implicit lazy val combine = new Semigroup[Iterable[T]] {
      def combine(x: Iterable[T], y: Iterable[T]): Iterable[T] = x ++ y
    }

    /**
      * Transformation Iterable[Validation[T]] => Validation[Iterable[T]].
      */
    def swap: Validation[Iterable[T]] = values match {
      case Nil => Validator.success(Iterable.empty)
      case head :: Nil => head.map(Iterable.apply(_))
      // TODO: this need to be done better way
      case _ => values.tail.foldLeft(values.head.map(Iterable.apply(_))) {
        case (v1, v2) => v1.combine(v2.map(Iterable.apply(_)))
      }
    }
  }

}
