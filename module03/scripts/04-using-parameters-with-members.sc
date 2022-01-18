trait Food { val name: String }
trait Fruit extends Food
trait Cereal extends Food

case class Apple(name: String) extends Fruit
case class Orange(name: String) extends Fruit
case class Muesli(name: String) extends Cereal

abstract class FoodBowl2 {
  type FOOD <: Food
  val food: FOOD
  def eat: String = s"Yummy ${food.name}"
}

class AppleBowl(val food: Apple) extends FoodBowl2 {
  type FOOD = Apple
}

/**
 * So type parameters can be used to initialize type members
 */
object BowlOfFood {
  def apply[F <: Food](f: F) = new FoodBowl2 {
    type FOOD = F
    override val food: FOOD = f
  }
}

val bowlOfAlpen: FoodBowl2 {
  type FOOD = Muesli
} = BowlOfFood(Muesli("alpen"))

val appleBowl: FoodBowl2 {
  type FOOD = Apple
} = BowlOfFood(Apple("Fiji"))
val orangeBowl: FoodBowl2 {
  type FOOD = Orange
} = BowlOfFood(Orange("Jaffa"))

val a1: Apple = appleBowl.food
// is identical too
val a2: appleBowl.FOOD = appleBowl.food
val a3:FoodBowl2#FOOD = appleBowl.food

// but does not compile:
//val o1: orangeBowl.FOOD = appleBowl.food
val o1: FoodBowl2#FOOD= appleBowl.food