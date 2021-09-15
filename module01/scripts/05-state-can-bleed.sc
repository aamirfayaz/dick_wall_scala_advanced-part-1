class Person(val name: String, var weight: Double) {
	override def toString: String = s"Person($name, $weight)"
}

val alice = new Person("Alice", 123)
val bob = new Person("Bob", 124)

val all = Seq(alice, bob)

def heaviestPerson(people: Seq[Person]): Person =
	people.maxBy(_.weight)


heaviestPerson(all)

bob.weight = 122

heaviestPerson(all)

case class Student(id: Int, marks: Int)

val l = List(Student(1, 9), Student(2, 99),Student(3, 112),Student(4, 10))
l.maxBy((x: Student) => x.marks)