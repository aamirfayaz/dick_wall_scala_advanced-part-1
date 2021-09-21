abstract class Food { val name: String }

abstract class Fruit extends Food
case class Banana(name: String) extends Fruit
case class Apple(name: String) extends Fruit

abstract class Cereal extends Food
case class Granola(name: String) extends Cereal
case class Muesli(name: String) extends Cereal

val fuji = Apple("Fuji")
val alpen = Muesli("Alpen")

def eat(f: Food): String = s"${f.name} eaten"

eat(fuji)

eat(alpen)

case class Bowl(food: Food) {
  override def toString = s"A bowl of yummy ${food.name}s"
  def contents = food
}
val fruitBowl: Bowl = Bowl(fuji)
val cerealBowl: Bowl = Bowl(alpen)
val r1: Food = fruitBowl.contents
val r2: Food = cerealBowl.contents

case class Bowl2[F](contents: F) {
  override def toString: String = s"A yummy bowl of ${contents}s"
}

val appleBowl: Bowl2[Apple] = Bowl2(fuji)
val muesliBowl: Bowl2[Muesli] = Bowl2(alpen)
appleBowl.contents
muesliBowl.contents

case class Bowl3[F <: Food](contents: F) {
  override def toString: String = s"A yummy bowl of ${contents.name}s"
}

val appleBowl: Bowl3[Apple] = Bowl3(fuji)
val muesliBowl: Bowl3[Muesli] = Bowl3(alpen)
appleBowl.contents
muesliBowl.contents

//val bowlOfNumbers = Bowl3(10) CTE