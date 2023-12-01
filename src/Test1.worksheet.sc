import scala.collection.mutable.ArrayBuffer
val full = """two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen"""

val values = full.split("\n").toList

var in = values(4)
val words =
  List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
val nbrs = (1 to 9).toList

val stringDigitReprs: Map[String, String] = Map(
  "one" -> "1",
  "two" -> "2",
  "three" -> "3",
  "four" -> "4",
  "five" -> "5",
  "six" -> "6",
  "seven" -> "7",
  "eight" -> "8",
  "nine" -> "9"
)

var replacing = true
do {
  val filtered =
    words.zipWithIndex
      .map(t => (in.indexOf(t._1), t._2))
      .filter(x => x._1 != -1)
      .sorted

  if (filtered.isEmpty)
    replacing = false
  else {
    val firstToReplace = words(filtered.head._2)
    in = in.replaceFirst(firstToReplace, stringDigitReprs(firstToReplace))
  }
} while (replacing)

