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

    // Top level parsing of each line
    for (line <- in) {
      val g: Game = line match {
        case s"Game $id: $tries" =>
          Game(id.toInt, makeTries(tries))
      }

      res += g
    }

    res.toList
  }

  // Testing  input
  val dataParsedSmall = parseInput(dataSmall)
  val dataParsedFull = parseInput(dataFull)

  // println(dataParsedSmall.mkString("\n"))

  def computePart1(data: List[Game]): Int = {
    def isPossible(g: Game): Boolean = {
      // The only valid games are those which have 12 or les reds, ...
      g.tries.map(_.nRed).forall(_ <= 12) &&
      g.tries.map(_.nGreen).forall(_ <= 13) &&
      g.tries.map(_.nBlue).forall(_ <= 14)
    }

    data
      .filter((g: Game) => isPossible(g))
      .map(_.id)
      .sum
  }

  println(s"Part 1 solution: ${computePart1(dataParsedFull)}")

  /////////// Second part
  def computePart2(data: List[Game]): Int = {
    data
      .map((g: Game) => {
        val maxR = g.tries.map(_.nRed).max
        val maxG = g.tries.map(_.nGreen).max
        val maxB = g.tries.map(_.nBlue).max
        maxR * maxG * maxB
      })
      .sum
  }

  println(s"Part 2 solution: ${computePart2(dataParsedFull)}")
}
