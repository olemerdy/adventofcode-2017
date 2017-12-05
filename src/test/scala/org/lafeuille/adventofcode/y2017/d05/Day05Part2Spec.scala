package org.lafeuille.adventofcode.y2017.d05

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day05Part2Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "My input" should {
    val result = 27502966

    s"solve in $result steps" in {
      Day05Part2.steps(Day05.instructions(Day05.myInput)) shouldBe result
    }
  }

  "Sample input" should {
    val instructions = Array(0, 3, 0, 1, -3)
    val result = 10

    s"solve in $result steps" in {
      Day05Part2.steps(instructions) shouldBe result
    }
  }

}
