package org.lafeuille.adventofcode.y2017.d12

import org.lafeuille.adventofcode.y2017.d12.Day12.Nodes

import scala.io.Source
import scala.io.Source.fromURL
import scala.util.matching.Regex

object Day12 {

  type Nodes = Map[Int, List[Int]]

  val regex: Regex = "(\\d+) <-> (\\d+(,\\s*\\d+)*)".r

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def nodes(input: Source): Nodes =
    input.getLines().map {
      case regex(id, connected) =>
        id.toInt -> connected.split(",\\s*").map(_.toInt).toList
    }.toMap

}

object Day12Part1 extends App {

  def shortestPath(from: Int, to: Int = 0, nodes: Nodes): List[Int] = {
    def rec(path: List[Int], to: Int, visited: Set[Int], nodes: Nodes): List[Int] = {
      ???
    }

    rec(from :: Nil, to, Set(), nodes)
  }

}
