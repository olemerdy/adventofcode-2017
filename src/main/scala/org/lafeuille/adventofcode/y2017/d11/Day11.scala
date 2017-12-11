package org.lafeuille.adventofcode.y2017.d11

import org.lafeuille.adventofcode.y2017.d11.HexDirection.HexDirection

import scala.annotation.tailrec
import scala.io.Source
import scala.io.Source.fromURL

object HexDirection extends Enumeration {
  type HexDirection = Value
  val NW, N, NE, SE, S, SW = Value
}

case class HexCoordinate(x: Int, y: Int, z: Int) {
  require(x + y + z == 0)

  def move(to: HexDirection): HexCoordinate = to match {
    case HexDirection.NW => copy(x = x - 1, y = y + 1)
    case HexDirection.N => copy(y = y + 1, z = z - 1)
    case HexDirection.NE => copy(x = x + 1, z = z - 1)
    case HexDirection.SE => copy(x = x + 1, y = y - 1)
    case HexDirection.S => copy(y = y - 1, z = z + 1)
    case HexDirection.SW => copy(x = x - 1, z = z + 1)
  }

  def distance(to: HexCoordinate = HexCoordinate(0, 0, 0)): Int =
    (Math.abs(x - to.x) + Math.abs(y - to.y) + Math.abs(z - to.z)) / 2
}

// @see http://devmag.org.za/2013/08/31/geometry-with-hex-coordinates/
object Day11 {

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def directions(input: Source): List[HexDirection] =
    myInput.mkString.split(",").map {
      case "nw" => HexDirection.NW
      case "n" => HexDirection.N
      case "ne" => HexDirection.NE
      case "se" => HexDirection.SE
      case "s" => HexDirection.S
      case "sw" => HexDirection.SW
    }.toList

}

object Day11Part1 extends App {

  def finalCoord(directions: List[HexDirection]): HexCoordinate = {

    @tailrec
    def rec(coord: HexCoordinate, directions: List[HexDirection]): HexCoordinate =
      directions match {
        case Nil => coord
        case h :: tail => rec(coord.move(h), tail)
      }

    rec(HexCoordinate(0, 0, 0), directions)
  }

  println(finalCoord(Day11.directions(Day11.myInput)).distance())
}

object Day11Part2 extends App {

  def highestDistance(directions: List[HexDirection]): Int = {

    @tailrec
    def rec(highest: Int, coord: HexCoordinate, directions: List[HexDirection]): Int =
      directions match {
        case Nil => highest
        case h :: tail => rec(Math.max(highest, coord.distance()), coord.move(h), tail)
      }

    rec(0, HexCoordinate(0, 0, 0), directions)
  }

  println(highestDistance(Day11.directions(Day11.myInput)))
}
