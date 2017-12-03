package org.lafeuille.adventofcode.y2017.d03

import org.scalatest.{Matchers, WordSpec}
import org.scalatest.prop.TableDrivenPropertyChecks

class Day03Part1Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "Sample input with 1st method" should {

    val squares = Table(
      ("Square", "Moves"),
      (1, 0),
      (12, 3),
      (23, 2),
      (1024, 31)
    )

    forAll(squares) { (square, moves) =>
      s"have square $square be reachable in $moves moves" in {
        Day03Part1.moves(square) shouldBe moves
      }
    }

  }

}
