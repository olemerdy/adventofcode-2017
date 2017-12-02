package org.lafeuille.adventofcode.y2017.d01

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day01Part1Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "Sample input" should {

    val samples = Table(
      ("Numbers", "Sum"),
      (List(1, 1, 2, 2), 3),
      (List(1, 1, 1, 1), 4),
      (List(1, 2, 3, 4), 0),
      (List(9, 1, 2, 1, 2, 1, 2, 9), 9)
    )

    forAll(samples) { (numbers, sum) =>
      s"have $numbers sum be $sum" in {
        Day01Part1.compute(numbers) shouldBe sum
      }
    }
  }

}
