package org.lafeuille.adventofcode.y2017.d03

import org.lafeuille.adventofcode.y2017.core.Position
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day03Part2Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "My input" should {

    val result = 349975

    s"should have first value greater than ${Day03.myInput} be $result" in {
      Day03Part2.firstValueGreaterThan(Day03.myInput) shouldBe result
    }
  }

  "Sample input with 2nd method" should {

    val squares = Table(
      ("Position", "Value"),
      (Position(1, 1), 2),
      (Position(0, 1), 4),
      (Position(-1, 1), 5),
      (Position(-1, 0), 10),
      (Position(-1, -1), 11),
      (Position(0, -1), 23),
      (Position(1, -1), 25)
    )

    val spiral = Day03Part2.spiral(1000)

    forAll(squares) { (position, value) =>
      s"have position $position hold value $value" in {
        spiral.value(position) shouldBe Some(value)
      }
    }

  }

}
