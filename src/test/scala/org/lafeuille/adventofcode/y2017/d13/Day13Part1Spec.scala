package org.lafeuille.adventofcode.y2017.d13

import org.scalatest.{Matchers, WordSpec}

class Day13Part1Spec extends WordSpec with Matchers {

  "My input" should {
    val myResult = 0

    s"have severity $myResult" in {
      Day13Part1.severity(Day13.layers(Day13.myInput)) shouldBe myResult
    }
  }

  "Sample input" should {
    val layers = Map(
      0 -> 3,
      1 -> 2,
      4 -> 4,
      6 -> 4
    )
    val result = 24

    s"have severity $result" in {
      Day13Part1.severity(layers) shouldBe result
    }
  }

}
