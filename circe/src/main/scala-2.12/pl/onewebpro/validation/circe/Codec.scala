package pl.onewebpro.validation.circe

import io.circe.Json
import pl.onewebpro.validation.circe.formatter._
import pl.onewebpro.validation.core.data.{Source, SourceCodec, Formatter}

import scala.language.implicitConversions
import scala.reflect.ClassTag

object Codec extends SourceCodec[Json, Option[Json]] {

  override implicit def toSource(source: Json): Source[SourceT, Contract] = new CirceSource(source)

  override implicit val stringFormat: Formatter[Contract, String] = StringFormatter

  override implicit val shortFormat: Formatter[Contract, Short] = ShortFormatter

  override implicit val intFormat: Formatter[Contract, Int] = IntFormatter

  override implicit val doubleFormat: Formatter[Contract, Double] = DoubleFormatter

  override implicit val booleanFormat: Formatter[Contract, Boolean] = BooleanFormatter

  override implicit def collectionFormat[A: ClassTag]
  (implicit fieldFormat: Formatter[Contract, A]): Formatter[Contract, Iterable[A]] =
    new CollectionFormatter
}
