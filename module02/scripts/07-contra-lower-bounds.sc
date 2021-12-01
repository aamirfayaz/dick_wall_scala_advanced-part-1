trait Food {
  val name: String
  override def toString = s"Yummy $name"
}
trait Fruit extends Food
case class Apple(name: String) extends Fruit
case class Orange(name: String) extends Fruit

case class M(x: Int) extends Apple("")

trait Sink[-T] { outer =>

  def send(item: T): String
  def andThenSink[U <: T](other: Sink[U]): Sink[U] = {
    (item: U) => outer.send(item) + " and then " + other.send(item)
  }
}
//SAM
/**
trait Json
case class JsonString(x: String) extends Json
trait JsonWriter[A] {
  def write(a: A): Json
}

object JsonInstance {
  implicit val intWriter: JsonWriter[String] = (i: String) => JsonString(i)
}
 trait Action {
  def act(x: Int):Int
}
val x:Action = (x: Int) => x + 10
 */


class AppleSink extends Sink[Apple] {
  def send(item: Apple) = s"Coring and eating ${item.name}"
}
class OrangeSink extends Sink[Orange] {
  def send(item: Orange) = s"Juicing and drinking ${item.name}"
}
class FruitSink extends Sink[Fruit] {
  def send(item: Fruit) = s"Eating a healthy ${item.name}"
}
class AnySink extends Sink[Any] {
  def send(item: Any) = s"Sending ${item.toString}"
}

def sinkAnApple(sink: Sink[Apple]): String = {
  sink.send(Apple("Fuji"))
}
sinkAnApple(new AppleSink())
//if single instance only is there, we can make class as object

//sinkAnApple(OrangeSink)  // this shouldn't work

sinkAnApple(new FruitSink())
//val newSink: Sink[Apple] = FruitSink.andThenSink(AppleSink)
val x: FruitSink = new FruitSink()
val a: AppleSink = new AppleSink()
val o: OrangeSink = new OrangeSink()
val newSink: Sink[M] = a.andThenSink[M](x)
//val newSink: Sink[M] = a.andThenSink[M](o)
//sinkAnApple(newSink)

