package org.lafeuille.adventofcode.y2017.d02

import org.lafeuille.adventofcode.y2017.d02.Day02.{myInput, spreadsheet}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day02Part1Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "My input" should {
    val myResult = 32121

    s"have checksum $myResult" in {
      Day02Part1.checksum(spreadsheet(myInput)) shouldBe myResult
    }
  }

  "Sample input" should {
    val row1 = List(5, 1, 9, 5)
    val row2 = List(7, 5, 3)
    val row3 = List(2, 4, 6, 8)

    val rows = Table(
      ("Index", "Row", "Checksum"),
      (1, row1, 8),
      (2, row2, 4),
      (3, row3, 6),
    )

    forAll(rows) { (index, row, checksum) =>
      s"have row $index checksum be $checksum" in {
        Day02Part1.rowChecksum(row) shouldBe checksum
      }
    }

    val spreadsheet = row1 :: row2 :: row3 :: Nil
    val result = 18

    s"have checksum $result" in {
      Day02Part1.checksum(spreadsheet) shouldBe result
    }
  }

}
