abstract class Food extends Product with Serializable { val name: String }

abstract class Fruit extends Food
case class Banana(name: String) extends Fruit
case class Apple(name: String) extends Fruit

abstract class Cereal extends Food
case class Granola(name: String) extends Cereal
case class Muesli(name: String) extends Cereal

val fuji = Apple("Fuji")
val alpen = Muesli("Alpen")

trait CombineWith[T] {
  val item: T
  def combineWith(another: T): T
}
case class CombineWithInt(item: Int) extends CombineWith[Int] {
  def combineWith(another: Int) = item + another
}
val cwi: CombineWith[Int] = CombineWithInt(10)

/* invariant behavior

val cwo: CombineWith[Any] = CombineWithInt(10)
cwo.combineWith("ten")

trait CombineWith2[+T] {
  val item: T
  def combineWith(another: T): T // convariant type occurs in contravariant type
}
*/

// Bounds revisited

val ints = List(1,2,3,4)

val anyvals: List[AnyVal] = true :: ints

val anys: List[Any] = "hello" :: anyvals

case class MixedFoodBowl[+F <: Food](food1: F, food2: F) {
  override def toString: String = s"${food1.name} mixed with ${food2.name}"
}

case class FoodBowl[+F <: Food](food: F) {
  override def toString: String = s"A bowl of ${food.name}"

  //def mix(other: F): MixedFoodBowl[F] = MixedFoodBowl(food, other)
  def mix[M >: F <: Food](other: M) = MixedFoodBowl[M](food, other)
}

val apple: Apple = Apple("Fuji")
val banana: Banana = Banana("Chiquita")

val bananaFoodBowl: FoodBowl[Banana] = FoodBowl(banana)

//val r: MixedFoodBowl[Fruit] = FoodBowl(banana).mix[Apple](apple) // Apple is not >: to Banana
val r: MixedFoodBowl[Fruit] = FoodBowl(banana).mix[Fruit](apple) // Fruit is least upper bound LUB
val ans: MixedFoodBowl[Food] = FoodBowl(apple).mix(alpen)
