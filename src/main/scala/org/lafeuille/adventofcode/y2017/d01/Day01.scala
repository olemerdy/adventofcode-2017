package org.lafeuille.adventofcode.y2017.d01

import org.lafeuille.adventofcode.y2017.d01.Day01.{myInput, numbers}
import org.lafeuille.adventofcode.y2017.d01.Day01Part1.compute

import scala.io.Source
import scala.io.Source.fromURL

object Day01 {

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def numbers(input: Source): List[Int] =
    input.toList.map(_.toString).map(_.toInt)

  def compute(numbers: List[Int])(zipFunction: List[Int] => List[Int]): Int = {
    numbers.zip(zipFunction(numbers))
      .filter { case (x, y) => x == y }
      .map { case (x, _) => x }
      .sum
  }

}

object Day01Part1 extends App {

  def compute(numbers: List[Int]): Int =
    Day01.compute(numbers) { list =>
      list.tail ::: list.head :: Nil
    }

  println(compute(numbers(myInput)))
}

object Day01Part2 extends App {

  def compute(numbers: List[Int]): Int =
    Day01.compute(numbers) { list =>
      val (start, end) = list.splitAt(list.size / 2)
      end ::: start
    }

  println(compute(numbers(myInput)))
}
