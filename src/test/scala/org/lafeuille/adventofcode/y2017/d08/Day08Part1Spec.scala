package org.lafeuille.adventofcode.y2017.d08

import org.scalatest.{Matchers, WordSpec}

import scala.io.Source.fromURL

class Day08Part1Spec extends WordSpec with Matchers {

  "My input" should {
    val myResult = 5102

    s"have largest value be $myResult" in {
      Day08Part1.largestValue(Day08.instructions(Day08.myInput)) shouldBe myResult
    }
  }

  "Sample input" should {
    val instructions = Day08.instructions(fromURL(getClass.getResource("sample-input.txt")))
    val result = 1

    s"have largest value be $result" in {
      Day08Part1.largestValue(instructions) shouldBe result
    }
  }

}
