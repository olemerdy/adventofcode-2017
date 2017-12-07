package org.lafeuille.adventofcode.y2017.d07

import scala.io.Source
import scala.io.Source.fromURL
import scala.util.matching.Regex

case class NodeInfo(id: String, weight: Int, children: List[String])

object Day07 {

  val regex: Regex =
    "([a-z]+) \\((\\d+)\\)( -> (([a-z]+)(,\\s*([a-z]+))*))?".r

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def parse(line: String): NodeInfo = {
    val matches = Day07.regex.findAllIn(line)
    NodeInfo(
      id = matches.group(1),
      weight = matches.group(2).toInt,
      children = Option(matches.group(4)).map(_.split(",\\s*").toList).getOrElse(Nil)
    )
  }

  def nodes(input: Source): List[NodeInfo] =
    input.getLines().map(parse).toList

}

object Day07Part1 extends App {
  println(Day07.nodes(Day07.myInput))
}
