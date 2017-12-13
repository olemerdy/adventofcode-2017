package org.lafeuille.adventofcode.y2017.d13

import scala.io.Source
import scala.util.matching.Regex

object Day13 {

  val regex: Regex = "(\\d+):\\s*(\\d+)".r

  def myInput: Source =
    Source.fromURL(getClass.getResource("input.txt"))

  def layers(input: Source): Map[Int, Int] =
    input.getLines().map {
      case regex(layer, range) => layer.toInt -> range.toInt
    }.toMap

}

object Day13Part1 extends App {

  def severity(layers: Map[Int, Int]): Int =
    ???

  println(severity(Day13.layers(Day13.myInput)))

}