import isc.advent.Utils
import scala.collection.mutable.ArrayBuffer
import os.makeDir

object Day2 extends App {
  val day = 2
  val dataSmall: List[String] = Utils.getSimpleData(day)
  val dataFull: List[String] = Utils.getFullData(day)

  // Data model
  case class Game(id: Int, tries: List[Try])
  case class Try(nRed: Int, nGreen: Int, nBlue: Int)

  def parseInput(in: List[String]): List[Game] = {
    val res = ArrayBuffer.empty[Game]

    def makeTries(s: String): List[Try] = {

      def makeTry(s: String): Try = {
        val allThePairs: Map[String, Int] =
          s.split(",")
            .map(x =>        
              // We need a map for colors, as they are mixed
              x.trim match {
                case s"$n $color" => (color, n.toInt)
              }
            )
            .toMap

        return Try(
          allThePairs.getOrElse("red", 0),
          allThePairs.getOrElse("green", 0),
          allThePairs.getOrElse("blue", 0)
        )
      }

      s.split(";").map(makeTry(_)).toList
    }

    for (line <- in) {
      val g: Game = line match {
        case s"Game $id: $tries" =>
          Game(id.toInt, makeTries(tries))
      }

      res += g
    }

    res.toList
  }

  // Testint input

  println(parseInput(dataSmall).mkString("\n"))

  def computePart1(data: List[String]): Int = ???

  // println(s"Part 1 solution: ${computePart1(dataFull)}")

  // /////////// Second part
  // def computePart2(data: List[String]): Int = ???

  // // This answer is off by 1, I don't know why
  // println(s"Part 2 solution: ${computePart2(dataFull)}")
}
