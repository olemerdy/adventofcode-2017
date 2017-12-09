package org.lafeuille.adventofcode.y2017.d08

import org.lafeuille.adventofcode.y2017.d08.Day08.{instructions, myInput}
import org.lafeuille.adventofcode.y2017.d08.Comparison.Comparison
import org.lafeuille.adventofcode.y2017.d08.Operation.Operation

import scala.annotation.tailrec
import scala.io.Source
import scala.io.Source.fromURL
import scala.util.matching.Regex

case class Instruction(action: Action, condition: Condition)

object Operation extends Enumeration {
  type Operation = Value
  val `+`, `-` = Value
}

object Comparison extends Enumeration {
  type Comparison = Value
  val `==`, `>`, `>=`, `<=`, `<`, `!=` = Value
}

case class Action(register: String, operation: Operation, value: Int) {
  def apply(map: Map[String, Int]): Map[String, Int] = map.updated(register, operation match {
    case Operation.`+` => map(register) + value
    case Operation.`-` => map(register) + value
  })
}

case class Condition(register: String, comp: Comparison, value: Int) {
  def apply(map: Map[String, Int]): Boolean = comp match {
    case Comparison.`==` => map(register) == value
    case Comparison.`!=` => map(register) != value
    case Comparison.`<=` => map(register) <= value
    case Comparison.`>=` => map(register) >= value
    case Comparison.`<` => map(register) < value
    case Comparison.`>` => map(register) > value
  }
}

object Day08 {

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  val regex: Regex = "([a-z]+) (dec|inc) (-?[0-9]+) if ([a-z]+) (==|!=|>=|>|<|<=) (-?[0-9]+)".r

  def instructions(input: Source): List[Instruction] =
    input.getLines().map { case regex(r1, op1, v1, r2, op2, v2) =>
      Instruction(
        Action(r1, op1 match {
          case "dec" => Operation.`-`
          case "inc" => Operation.`+`
        }, v1.toInt),
        Condition(r2, op2 match {
          case "==" => Comparison.`==`
          case "!=" => Comparison.`!=`
          case ">=" => Comparison.`>=`
          case "<=" => Comparison.`<=`
          case ">" => Comparison.`>`
          case "<" => Comparison.`<`
        }, v2.toInt)
      )
    }.toList
}

object Day08Part1 extends App {

  def largestValue(instructions: List[Instruction]): Int = {

    @tailrec
    def rec(map: Map[String, Int], instructions: List[Instruction]): Map[String, Int] = instructions match {
      case Nil => map
      case h :: tail if h.condition(map) => rec(h.action(map), tail)
      case _ :: tail => rec(map, tail)
    }

    val map = rec(Map[String, Int]().withDefaultValue(0), instructions)
    map.values.max
  }

  println(largestValue(instructions(myInput)))
}
