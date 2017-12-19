package org.lafeuille.adventofcode.y2017.d18

import org.scalatest.{Matchers, WordSpec}

import scala.io.Source

class Day18Part1Spec extends WordSpec with Matchers {

  "Sample input" should {
    val instructions = Day18.instructions(Source.fromURL(getClass.getResource("sample-input.txt")))
    val result = 4

    s"should have first recover be $result" in {
      Day18Part1.firstRecover(instructions) shouldBe result
    }
  }

}
