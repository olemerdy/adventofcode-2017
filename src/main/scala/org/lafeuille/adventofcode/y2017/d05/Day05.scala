package org.lafeuille.adventofcode.y2017.d05

import org.lafeuille.adventofcode.y2017.d05.Day05.{instructions, myInput}

import scala.io.Source
import scala.io.Source.fromURL

object Day05 {

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def instructions(input: Source): Array[Int] =
    myInput.getLines.map(line => line.toInt).toArray

}

object Day05Part1 extends App {

  def steps(instructions: Array[Int]): Int = {
    var steps = 0
    var idx = 0
    while (idx >= 0 && idx < instructions.length) {
      val instruction = instructions(idx)
      instructions(idx) = instruction + 1
      idx += instruction
      steps += 1
    }
    steps
  }

  println(instructions(myInput).mkString(","))

}

object Day05Part2 extends App {

  def steps(instructions: Array[Int]): Int = {
    var steps = 0
    var idx = 0
    while (idx >= 0 && idx < instructions.length) {
      val instruction = instructions(idx)
      instructions(idx) =
        if (instruction > 2)
          instruction - 1
        else
          instruction + 1
      idx += instruction
      steps += 1
    }
    steps
  }

  println(instructions(myInput).mkString(","))

}
