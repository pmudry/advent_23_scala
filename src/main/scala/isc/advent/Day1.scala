import isc.advent.Utils
import scala.collection.mutable.ArrayBuffer

object Day1_part1 extends App {
  val day = 1
  val dataSmall: List[String] = Utils.getSimpleData(day)
  val dataFull: List[String] = Utils.getFullData(day)

  def extractNumber(in: String): Int = {
    val t = in.filter(_.isDigit)

    if (t.length() == 1)
      Integer.parseInt(t + t)
    else
      List(t.head, t.last)
        .foldLeft("")((x, y) => x + y)
        .toInt
  }

  def computePart1(in: List[String]): Int = {
    var s: Int = 0

    for (d <- in)
      s += extractNumber(d)

    s
  }

  println(s"Part 1 solution: ${computePart1(dataFull)}")

  /////////// Second part
  def replaceFirstTextByDigits(in: String, invert: Boolean = false): String = {
    var words =
      List(
        "one","two","three","four","five",
        "six","seven","eight","nine"
      )

    var res = in

    if (invert) {
      words = words.map(_.reverse)
      res = res.reverse
    }

    val nbrs = (1 to 9).toList

    // Creates something like ("one" -> "1", "two" -> "2")
    val stringDigitReprs: Map[String, String] =
      (for (i <- words.indices)
        yield (words(i) -> nbrs(i).toString())).toMap

    val filtered =
      words.zipWithIndex
        .map(t => (res.indexOf(t._1), t._2))
        .filter(x => x._1 != -1)
        .sorted

    if (filtered.nonEmpty) {
      val firstToReplace = words(filtered.head._2)
      res = res.replaceFirst(firstToReplace, stringDigitReprs(firstToReplace))
    }

    if(invert)
      res.reverse
    else
      res
  }

  def replaceLastTextByDigit(s: String) : String = {
    replaceFirstTextByDigits(s, true)
  }

  def removeOthers(x: String): String = x.filter(_.isDigit)

  def makeNumber(in: String): Int = Integer.parseInt("" + in.head + in.last)

  // Do some basic checks
  assert(replaceFirstTextByDigits("asdftwofour") == "asdf2four")
  assert(replaceFirstTextByDigits("fourasdffour") == "4asdffour")
  assert(replaceFirstTextByDigits("123fourttt") == "1234ttt")
  assert(makeNumber(removeOthers(replaceLastTextByDigit(replaceFirstTextByDigits("fpxmmbthreeninethreefour3six")))) == 36)
  
  // That one was a pain
  assert(replaceLastTextByDigit("twone") == "tw1")

  val sol = dataFull
     .map(replaceFirstTextByDigits(_))
     .map(replaceLastTextByDigit(_))
     .map(removeOthers(_))
     .map(makeNumber(_))

  // This answer is off by 1, I don't know why
  println(s"Part 2 solution: ${sol.sum}")
}
