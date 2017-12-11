package org.lafeuille.adventofcode.y2017.d07

import org.lafeuille.adventofcode.y2017.d07.Day07.{myInput, nodeInfos}

import scala.io.Source
import scala.io.Source.fromURL
import scala.util.matching.Regex

case class NodeInfo(name: String, weight: Int, children: List[String]) {
  lazy val isLeaf: Boolean = children.isEmpty

  lazy val isRoot: Boolean = !isLeaf
}

object Day07 {

  val regex: Regex =
    "([a-z]+) \\((\\d+)\\)( -> (([a-z]+)(,\\s*([a-z]+))*))?".r

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def parse(line: String): NodeInfo = {
    val matches = Day07.regex.findAllIn(line)
    NodeInfo(
      name = matches.group(1),
      weight = matches.group(2).toInt,
      children = Option(matches.group(4)).map(_.split(",\\s*").toList).getOrElse(Nil)
    )
  }

  def nodeInfos(input: Source): List[NodeInfo] =
    input.getLines().map(parse).toList

}

object Day07Part1 extends App {

  def findRoot(nodeInfos: List[NodeInfo]): NodeInfo = {
    val roots = nodeInfos.filter(_.isRoot)
    val children = roots.flatMap(_.children)
    roots.filter(root => !children.contains(root.name)).head
  }

  println(findRoot(nodeInfos(myInput)))
}

case class Node(name: String, weight: Int, children: List[Node]) {
  lazy val totalWeight: Int =
    weight + children.map(_.totalWeight).sum
}

object Day07Part2 extends App {

  def rec(nodeNames: List[String], nodeInfos: Map[String, NodeInfo], nodes: Map[String, Node]): Map[String, Node] = {
    ???
  }

  def buildTree(nodeInfos: List[NodeInfo]): Node = {
    val nodes =
      rec(nodeInfos.map(_.name), nodeInfos.groupBy(_.name).mapValues(_.head), Map[String, Node]())
    ???
  }

}
