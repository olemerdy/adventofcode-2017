package org.lafeuille.adventofcode.y2017.d02

import org.lafeuille.adventofcode.y2017.d02.Day02.{myInput, spreadsheet}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day02Part2Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "My input with 2nd method" should {
    val myResult = 197

    s"have checksum $myResult" in {
      Day02Part2.checksum(spreadsheet(myInput)) shouldBe myResult
    }
  }

  "Sample input with 2nd method" should {

    val row1 = List(5, 9, 2, 8)
    val row2 = List(9, 4, 7, 3)
    val row3 = List(3, 8, 6, 5)

    val rows = Table(
      ("Row", "Checksum"),
      (row1, 4),
      (row2, 3),
      (row3, 2)
    )

    forAll(rows) { (row, checksum) =>
      s"have ${row.mkString(",")} checksum be $checksum" in {
        Day02Part2.rowChecksum(row) shouldBe checksum
      }
    }

    val spreadsheet = row1 :: row2 :: row3 :: Nil
    val result = 9

    s"have checksum be $result" in {
      Day02Part2.checksum(spreadsheet) shouldBe result
    }
  }

}
