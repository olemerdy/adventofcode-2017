package org.lafeuille.adventofcode.y2017.d06

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day06Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "My input" should {
    val redistributions = 4074
    val cycles = 2793

    s"have $redistributions redistributions and $cycles cycles" in {
      Day06.steps(Day06.banks(Day06.myInput)) shouldBe redistributions -> cycles
    }
  }

  "Sample input" should {
    val banks = List(0, 2, 7, 0)
    val redistributions = 5
    val cycles = 4

    s"see $banks have $redistributions redistributions and $cycles cycles" in {
      Day06.steps(banks) shouldBe redistributions -> cycles
    }
  }

}
