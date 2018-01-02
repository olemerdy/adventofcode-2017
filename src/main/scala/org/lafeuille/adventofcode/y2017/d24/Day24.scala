package org.lafeuille.adventofcode.y2017.d24

import org.lafeuille.adventofcode.y2017.d24.Day24.Component

import scala.io.Source
import scala.util.matching.Regex

object Day24 {

  type Component = (Int, Int)

  val pattern: Regex = "([0-9]+)/([0-9]+)".r

  def myInput: Source =
    Source.fromURL(getClass.getResource("input.txt"))

  def components(input: Source): Set[Component] =
    input.getLines().map {
      case pattern(a, b) => a.toInt -> b.toInt
    }.toSet

}

object Day24Part1 extends App {

  def strongestBridge(components: Set[Component]): Int =
    ???

  println(strongestBridge(Day24.components(Day24.myInput)))

}
