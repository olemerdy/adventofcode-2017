package org.lafeuille.adventofcode.y2017.d11

import org.scalatest.{Matchers, WordSpec}

class Day11Part2Spec extends WordSpec with Matchers {

  "My input" should {
    val myResult = 1471

    s"be $myResult steps from origin" in {
      Day11Part2.highestDistance(Day11.directions(Day11.myInput)) shouldBe myResult
    }
  }

}
