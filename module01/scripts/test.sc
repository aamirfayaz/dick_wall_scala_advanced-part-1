class Foo {
  private[this] val yo: String = "Yo"
  def yo: String = "Hello"  // will not compile...
  def greet(name: String): String =
    s"$ yo $name"  // which yo would it call?
}

//vars, vals and defs share the same namespace