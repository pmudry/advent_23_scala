import isc.advent.Utils
import scala.collection.mutable.ArrayBuffer

object Day2 extends App {
  val day = 1
  val dataSmall: List[String] = Utils.getSimpleData(day)
  val dataFull: List[String] = Utils.getFullData(day)

  
  
  def computePart1(data : List[String]) : Int = ???

  println(s"Part 1 solution: ${computePart1(dataFull)}")

  /////////// Second part
  def computePart2(data : List[String]) : Int = ???
  
  
  // This answer is off by 1, I don't know why
  println(s"Part 2 solution: ${computePart2(dataFull)}")
}
