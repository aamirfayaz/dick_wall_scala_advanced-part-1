val h = "hello"

def say(hello: h.type): Unit = println(hello)

say(h)

// this does not work, even though the string contents are the same
//say("hello")

// A Shakespearian DSL that uses singleton types
val or = "or"
val to = "to"

object To {
  def be(o: or.type) = this
  def not(t: to.type) = this
  def be = "That is the question"
}

class LL {
  def me = ""
}
val obj = new LL
def takeLL(o: obj.type) = {
  ""
}

val obj2 = new LL
takeLL(obj)

//takeLL(obj2) CTE

To be or not to be

To not to be or be

object LL {

}
//It's also how you can refer to the type of a
// singleton object in Scala
def m: LL.type = LL


