package org.lafeuille.adventofcode.y2017.d08

import org.lafeuille.adventofcode.y2017.d08.Day08.{instructions, myInput}

import scala.io.Source
import scala.io.Source.fromURL

trait Instruction

object Day08 {

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def instructions(input: Source): List[Instruction] = ???
}

object Day08Part1 extends App {

  def largestValue(instructions: List[Instruction]): Int = ???

  println(largestValue(instructions(myInput)))
}
