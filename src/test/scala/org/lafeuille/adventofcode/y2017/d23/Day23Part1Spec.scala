package org.lafeuille.adventofcode.y2017.d23

import org.scalatest.{Matchers, WordSpec}

class Day23Part1Spec extends WordSpec with Matchers {

  "My input" should {
    val myResult = 8281

    s"have $myResult 'mul' invocations" in {
      Day23Part1.mulCount(Day23.instructions(Day23.myInput)) shouldBe myResult
    }
  }

}
