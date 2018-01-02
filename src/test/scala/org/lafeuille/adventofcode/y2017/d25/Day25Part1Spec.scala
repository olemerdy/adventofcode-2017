package org.lafeuille.adventofcode.y2017.d25

import org.lafeuille.adventofcode.y2017.core.RelativeDirection
import org.scalatest.{Matchers, WordSpec}

class Day25Part1Spec extends WordSpec with Matchers {

  "Sample input" should {
    val input = Input(
      state = 'A',
      checksumAfter = 6,
      rules = Set(
        Rule(
          state = 'A',
          actionsIf0 = Actions(
            write = 1,
            move = RelativeDirection.Right,
            continue = 'B'
          ),
          actionsIf1 = Actions(
            write = 0,
            move = RelativeDirection.Left,
            continue = 'B'
          )
        ),
        Rule(
          state = 'B',
          actionsIf0 = Actions(
            write = 1,
            move = RelativeDirection.Left,
            continue = 'A'
          ),
          actionsIf1 = Actions(
            write = 1,
            move = RelativeDirection.Right,
            continue = 'A'
          )
        )
      )
    )
    val result = 3

    s"have checksum be $result" in {
      Day25Part1.checksum(input) shouldBe result
    }
  }

  "My input" should {
    val myResult = 3578

    s"have checksum be $myResult" in {
      Day25Part1.checksum(Day25.myInput) shouldBe myResult
    }
  }

}
