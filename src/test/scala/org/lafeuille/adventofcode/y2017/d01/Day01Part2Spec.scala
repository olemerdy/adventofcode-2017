package org.lafeuille.adventofcode.y2017.d01

import org.lafeuille.adventofcode.y2017.d01.Day01.{myInput, numbers}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day01Part2Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "Sample input" should {

    val samples = Table(
      ("Numbers", "Sum"),
      (List(1, 2, 1, 2), 6),
      (List(1, 2, 2, 1), 0),
      (List(1, 2, 3, 4, 2, 5), 4),
      (List(1, 2, 3, 1, 2, 3), 12),
      (List(1, 2, 1, 3, 1, 4, 1, 5), 4),
    )

    forAll(samples) { (numbers, sum) =>
      s"have $numbers sum be $sum" in {
        Day01Part2.compute(numbers) shouldBe sum
      }
    }
  }

  "My input" should {
    val myResult = 1356

    s"have sum be $myResult" in {
      Day01Part2.compute(numbers(myInput)) shouldBe myResult
    }
  }

}
