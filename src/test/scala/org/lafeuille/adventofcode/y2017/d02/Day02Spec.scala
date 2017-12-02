package org.lafeuille.adventofcode.y2017.d02

import org.lafeuille.adventofcode.y2017.d02.Day02.{myInput, spreadsheet}
import org.scalatest.{Matchers, WordSpec}

class Day02Part1Spec extends WordSpec with Matchers {

  lazy val myResult = 32121

  "My input" should {
    s"have checksum $myResult" in {
      Day02Part1.checksum(spreadsheet(myInput)) shouldBe myResult
    }
  }

}

class Day02Part2Spec extends WordSpec with Matchers {

}
