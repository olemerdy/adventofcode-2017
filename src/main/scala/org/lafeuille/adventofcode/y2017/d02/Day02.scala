package org.lafeuille.adventofcode.y2017.d02

import org.lafeuille.adventofcode.y2017.d02.Day02.{Row, Spreadsheet, myInput, spreadsheet}

import scala.io.Source
import scala.io.Source.fromURL

object Day02 {

  type Row = List[Int]
  type Spreadsheet = List[Row]

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def spreadsheet(input: Source): Spreadsheet =
    input.getLines.map(_.split("\\t").map(_.toInt).toList).toList

}

object Day02Part1 extends App {

  def rowChecksum(row: Row) =
    row.max - row.min

  def checksum(spreadsheet: Spreadsheet) =
    spreadsheet.map(row => rowChecksum(row)).sum

  println(checksum(spreadsheet(myInput)))
}

object Day02Part2 extends App {

  def rowChecksum(row: Row): Int =
    row.combinations(2)
      .map {
        case x :: y :: _ if x < y => (x, y)
        case x :: y :: _ => (y, x)
      }
      .find {
        case (x, y) => y % x == 0
      }
      .map {
        case (x, y) => y / x
      }
      .getOrElse(0)

  def checksum(spreadsheet: Spreadsheet): Int =
    spreadsheet.map(row => rowChecksum(row)).sum

  println(checksum(spreadsheet(myInput)))
}
