package org.lafeuille.adventofcode.y2017.d22

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

import scala.io.Source

class Day22Part2Spec extends WordSpec with TableDrivenPropertyChecks with Matchers {

  "My input" should {
    val myResult = 2512103

    s"have $myResult burst" in {
      Day22Part2.bursts(Day22Part2.grid(Day22.myInput), 10000000) shouldBe myResult
    }
  }

  "Sample input" should {
    val grid = Day22Part2.grid(Source.fromURL(getClass.getResource("sample-input.txt")))

    val table = Table(
      ("Steps", "Bursts"),
      (100, 26),
      (10000000, 2511944)
    )

    forAll(table) { (steps, bursts) =>
      s"have $bursts bursts after $steps steps" in {
        Day22Part2.bursts(grid, steps) shouldBe bursts
      }
    }
  }

}
