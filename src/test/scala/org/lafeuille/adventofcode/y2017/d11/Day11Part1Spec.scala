package org.lafeuille.adventofcode.y2017.d11

import org.lafeuille.adventofcode.y2017.d11.HexDirection.{NE, S, SE, SW}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day11Part1Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "My input" should {
    val myResult = 643

    s"be $myResult steps from origin" in {
      Day11Part1.steps(Day11.directions(Day11.myInput)) shouldBe myResult
    }
  }

  "Sample input" should {

    val table = Table(
      ("Directions", ""),
      (List(NE, NE, NE), 3),
      (List(NE, NE, SW, SW), 0),
      (List(NE, NE, S, S), 2),
      (List(SE, SW, SE, SW, SW), 3)
    )

    forAll(table) { (directions, steps) =>
      s"have ${directions.mkString(",")} be $steps steps from origin" in {
        Day11Part1.steps(directions) shouldBe steps
      }
    }
  }

}
