package org.lafeuille.adventofcode.y2017.d02

import org.lafeuille.adventofcode.y2017.d02.Day02.{myInput, spreadsheet}

import scala.io.Source.fromURL

object Day02 {

  def myInput: Iterator[String] =
    fromURL(getClass.getResource("input.txt")).getLines()

  def spreadsheet(input: Iterator[String]): Seq[Seq[Int]] =
    input.map(_.split("\\t").map(_.toInt).toSeq).toSeq

}

object Day02Part1 extends App {

  def checksum(spreadsheet: Seq[Seq[Int]]) =
    spreadsheet.map(row => row.max - row.min).sum

  println(checksum(spreadsheet(myInput)))
}
