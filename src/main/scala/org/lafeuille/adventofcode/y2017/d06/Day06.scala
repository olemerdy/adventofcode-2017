package org.lafeuille.adventofcode.y2017.d06

import scala.annotation.tailrec
import scala.io.Source
import scala.io.Source.fromURL

object Day06 extends App {

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def banks(input: Source): List[Int] =
    input.mkString.split("\\s+").map(_.toInt).toList

  def steps(banks: List[Int]): (Int, Int) = {

    def redistribute(banks: List[Int]): List[Int] = {

      @tailrec
      def redistributeRec(idx: Int, blocks: Int, banks: List[Int]): List[Int] =
        if (blocks <= 0)
          banks
        else {
          val newIdx = if (idx + 1 < banks.size) idx + 1 else 0
          val newBanks = banks.updated(newIdx, banks(newIdx) + 1)
          redistributeRec(newIdx, blocks - 1, newBanks)
        }

      val i = banks.indexOf(banks.max)
      redistributeRec(i, banks(i), banks.updated(i, 0))
    }

    @tailrec
    def stepsRec(step: Int, banks: List[Int], visited: List[List[Int]]): (Int, Int) =
      if (visited.contains(banks))
        (step, visited.indexOf(banks) + 1)
      else
        stepsRec(step + 1, redistribute(banks), banks :: visited)

    stepsRec(0, banks, Nil)
  }

  println(steps(banks(myInput)))
}