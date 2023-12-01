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

  /**
    * This method is two-fold
    * It replaces the first sub-string which correspond to a number to a digit. If 
    * invert is true, we start from the end and we reverse what we are looking for
    * @param in
    * @param invert
    * @return
    */
  def replaceFirstTextByDigits(in: String, invert: Boolean = false): String = {
    var words =
      List(
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine"
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

    if (invert)
      res.reverse
    else
      res
  }

  def replaceLastTextByDigit(s: String): String =
    replaceFirstTextByDigits(s, true)

  def removeOthers(x: String): String = x.filter(_.isDigit)

  def compute2ndPart(in: List[String]): List[Int] = {
    val first_digits = in
      .map(replaceFirstTextByDigits(_))
      .map(removeOthers(_))

    val last_digits = in
      .map(replaceLastTextByDigit(_))
      .map(removeOthers(_))

    first_digits zip last_digits map { case (a, b) => ("" + a.head + b.last).toInt }
  }

  // Do some basic checks
  assert(replaceFirstTextByDigits("asdftwofour") == "asdf2four")
  assert(replaceFirstTextByDigits("fourasdffour") == "4asdffour")
  assert(replaceFirstTextByDigits("123fourttt") == "1234ttt")
  assert(replaceLastTextByDigit("twone") == "tw1")

  // The small example
  val demo = """two1nine
               |eightwothree
               |abcone2threexyz
               |xtwone3four
               |4nineeightseven2
               |zoneight234
               |7pqrstsixteen"""
  val demoData = demo.split("\n").toList

  assert(compute2ndPart(demoData).sum == 281)

  // A nasty one
  assert(compute2ndPart(List("11lttrkpcljbbrmponeightbb")).sum == 18)

  // This answer is off by 1, I don't know why
  println(s"Part 2 solution: ${compute2ndPart(dataFull).sum}")
}
