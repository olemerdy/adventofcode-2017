package org.lafeuille.adventofcode.y2017.d08

import org.lafeuille.adventofcode.y2017.d08.Day08.{getClass, instructions, myInput}
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.prop.TableDrivenPropertyChecks

import scala.io.Source.fromURL

class Day08Part1Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "Sample input" should {
    val result = 1

    "have largest value be" in {
      Day08Part1.largestValue(instructions(fromURL(getClass.getResource("sample-input.txt")))) shouldBe result
    }
  }

}
