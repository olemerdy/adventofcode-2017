package org.lafeuille.adventofcode.y2017.d22

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

import scala.io.Source

class Day22Part1Spec extends WordSpec with TableDrivenPropertyChecks with Matchers {

  "My input" should {
    val myResult = 5330

    s"have $myResult burst" in {
      Day22Part1.bursts(Day22Part1.grid(Day22.myInput), 10000) shouldBe myResult
    }
  }

  "Sample input" should {
    val grid = Day22Part1.grid(Source.fromURL(getClass.getResource("sample-input.txt")))

    val table = Table(
      ("Steps", "Bursts"),
      (7, 5),
      (70, 41),
      (10000, 5587)
    )

    forAll(table) { (steps, bursts) =>
      s"have $bursts bursts after $steps steps" in {
        Day22Part1.bursts(grid, steps) shouldBe bursts
      }
    }
  }

}
