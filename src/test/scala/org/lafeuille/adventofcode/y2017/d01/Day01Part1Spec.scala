package org.lafeuille.adventofcode.y2017.d01

import org.lafeuille.adventofcode.y2017.d01.Day01.{myInput, numbers}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day01Part1Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "Sample input with 1st method" should {

    val samples = Table(
      ("Numbers", "Sum"),
      (List(1, 1, 2, 2), 3),
      (List(1, 1, 1, 1), 4),
      (List(1, 2, 3, 4), 0),
      (List(9, 1, 2, 1, 2, 1, 2, 9), 9)
    )

    forAll(samples) { (numbers, sum) =>
      s"have ${numbers.mkString(",")} sum be $sum" in {
        Day01Part1.compute(numbers) shouldBe sum
      }
    }
  }

  "My input with 1st method" should {
    val myResult = 1034

    s"have sum be $myResult" in {
      Day01Part1.compute(numbers(myInput)) shouldBe myResult
    }
  }

}
