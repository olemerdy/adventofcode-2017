package org.lafeuille.adventofcode.y2017.d10

import scala.io.Source
import scala.io.Source.fromURL

object Day10 {

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def lengths(input: Source): List[Int] =
    input.mkString.split(",").map(_.toInt).toList

}

object Day10Part1 extends App {

  def productOfFirstTwo(lengths: List[Int]): Int = ???

}
