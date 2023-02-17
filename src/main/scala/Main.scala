import akka.http.scaladsl.model.Uri
import io.github.vigoo.desert.BinaryCodec
import io.github.vigoo.desert.custom.{DeserializationContext, SerializationContext, read, write}
import io.github.vigoo.desert.zioschema.DerivedBinaryCodec
import zio.schema.{DeriveSchema, Schema}

import java.util.Base64

object Main {
  implicit val uriSchema: Schema[Uri] = Schema.fail("not supported by zio")

  implicit val uriSerializer: BinaryCodec[Uri] = new BinaryCodec[Uri] {
    override def deserialize()(implicit ctx: DeserializationContext): Uri = {
      val urlString = read[String]()
      Uri(urlString)
    }

    override def serialize(value: Uri)(implicit context: SerializationContext): Unit = {
      val toSerialize = value.toString()
      write(toSerialize)
    }
  }

  case class TestClass(uri: Uri, stringP: String)

  implicit val testClassSchema: Schema[TestClass] = DeriveSchema.gen
  implicit val testClassCodec: BinaryCodec[TestClass] = DerivedBinaryCodec.derive
  def main(args: Array[String]): Unit = {
    import io.github.vigoo.desert._
    val serialized = serializeToArray(TestClass(Uri("https://google.com"), "otherParam"))
    println(Base64.getEncoder.encodeToString(serialized.toOption.getd))
  }
}
