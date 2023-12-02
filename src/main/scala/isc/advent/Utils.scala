package isc.advent

import scala.io.Source

object Utils {
  val path = "./res/"

  def getSimpleData(day: Int) = getData(day, path, false)
  def getFullData(day: Int) = getData(day, path, true)

  private def getData(
      day: Int,
      path: String = path,
      full: Boolean
  ): List[String] = {

    var fullPath = s"${path}input_day${day}"

    if (!full)
      fullPath += "_simple"
    else
      fullPath += ""

    fullPath += ".txt"

    Source.fromFile(fullPath).getLines().toList
  }
}
