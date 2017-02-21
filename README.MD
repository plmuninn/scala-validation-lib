# Validation Library

Validation library inspired by [Scala forms](https://playframework.com/documentation/2.5.x/ScalaForms). Based on [cats](http://typelevel.org/cats/) 
validation interface.

## Description
My goal was to create library with similar syntax like [Scala forms](https://playframework.com/documentation/2.5.x/ScalaForms) together with type checking on compilation level, but for various source of 
data. For now I created codecs for [circe](circe.github.io/circe/) and `Map[String, Any]` sources. 

Lib is not handling multi complex structures (like class in class).

## Example

```scala
import pl.onewebpro.validation._
import pl.onewebpro.validation.basic.codec.MapCodec._

case class TestClass(name: String, age: Option[Int])

val validationSchema = schema(
  "name" -> nonEmptyString,
  "age" -> optional(of[Int])
)(TestClass.apply)(TestClass.unapply)

val data = Map("name" -> "Frank", "age" -> 18)

validationSchema.validate(data)

```

## What is missing or should be fixed

* Multi structures (class inside class etc.)
* Better handling of scala collections (now only Iterable is support)
* Better source interface design for extracting data
* Better type mapping for results
