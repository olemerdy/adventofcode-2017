package org.lafeuille.adventofcode.y2017.d01

import org.lafeuille.adventofcode.y2017.d01.Day01.{myInput, numbers}

import scala.io.Source
import scala.io.Source.fromURL

object Day01 {

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def numbers(input: Source): List[Int] =
    input.toList.map(_.toInt)

}

object Day01Part1 extends App {

  def compute(numbers: List[Int]): Int = {
    numbers.zip(numbers.tail ::: numbers.head :: Nil)
      .filter { case (x, y) => x == y }
      .map { case (x, _) => x }
      .sum
  }

  println(compute(numbers(myInput)))
}
