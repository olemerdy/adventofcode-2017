package org.lafeuille.adventofcode.y2017.d23

import org.lafeuille.adventofcode.y2017.d23.Registry.getValue

import scala.annotation.tailrec
import scala.io.Source
import scala.util.matching.Regex

object Registry {
  def getValue(values: Map[Char, BigInt], source: Either[Char, BigInt]): BigInt =
    source match {
      case Left(c) => values.getOrElse(c, BigInt(0))
      case Right(i) => i
    }
}

case class Registry
(
  values: Map[Char, BigInt] = Map(),
  mulCount: BigInt = 0,
  instruction: BigInt = 0
)

sealed trait Instruction {
  def update(reg: Registry): Registry
}

object Set {
  val regex: Regex = "set ([a-z]) (([a-z])|(\\-?[0-9]+))".r
}

case class Set(target: Char, source: Either[Char, BigInt]) extends Instruction {
  override def update(reg: Registry): Registry =
    reg.copy(
      values = reg.values.updated(target, getValue(reg.values, source)),
      instruction = reg.instruction + 1
    )
}

object Subtract {
  val regex: Regex = "sub ([a-z]) (([a-z])|(\\-?[0-9]+))".r
}

case class Subtract(target: Char, source: Either[Char, BigInt]) extends Instruction {
  override def update(reg: Registry): Registry =
    reg.copy(
      values = reg.values.updated(target, reg.values.getOrElse(target, BigInt(0)) - getValue(reg.values, source)),
      instruction = reg.instruction + 1
    )
}

object Multiply {
  val regex: Regex = "mul ([a-z]) (([a-z])|(\\-?[0-9]+))".r
}

case class Multiply(target: Char, source: Either[Char, BigInt]) extends Instruction {
  override def update(reg: Registry): Registry =
    reg.copy(
      values = reg.values.updated(target, reg.values.getOrElse(target, BigInt(0)) * getValue(reg.values, source)),
      mulCount = reg.mulCount + 1,
      instruction = reg.instruction + 1
    )
}

object Jump {
  val regex: Regex = "jnz (([a-z])|(\\-?[0-9]+)) (([a-z])|(\\-?[0-9]+))".r
}

case class Jump(ref: Either[Char, BigInt], offset: Either[Char, BigInt]) extends Instruction {
  override def update(reg: Registry): Registry =
    reg.copy(instruction = reg.instruction + (
      if (getValue(reg.values, ref) == 0)
        1
      else
        getValue(reg.values, offset)
      )
    )
}

object Day23 {

  def myInput: Source =
    Source.fromURL(getClass.getResource("input.txt"))

  def source(sc: String, si: String): Either[Char, BigInt] =
    if (sc == null)
      Right(si.toInt)
    else
      Left(sc.head)

  def instructions(input: Source): List[Instruction] =
    input.getLines().map {
      case Jump.regex(_, tc, ti, _, sc, si) =>
        Jump(source(tc, ti), source(sc, si))
      case Multiply.regex(t, _, sc, si) =>
        Multiply(t.head, source(sc, si))
      case Set.regex(t, _, sc, si) =>
        Set(t.head, source(sc, si))
      case Subtract.regex(t, _, sc, si) =>
        Subtract(t.head, source(sc, si))
    }.toList

}

object Day23Part1 extends App {

  def mulCount(instructions: List[Instruction]): BigInt = {
    @tailrec
    def rec(instructions: List[Instruction], reg: Registry): Registry = {
      if (reg.instruction < 0 || reg.instruction >= instructions.length)
        reg
      else
        rec(instructions, instructions(reg.instruction.toInt).update(reg))
    }

    rec(instructions, Registry()).mulCount
  }

  println(mulCount(Day23.instructions(Day23.myInput)))

}

object Day23Part2 extends App {

  def lastValue(instructions: List[Instruction]): BigInt = {
    @tailrec
    def rec(instructions: List[Instruction], reg: Registry): Registry = {
      if (reg.instruction < 0 || reg.instruction >= instructions.length)
        reg
      else
        rec(instructions, instructions(reg.instruction.toInt).update(reg))
    }

    rec(instructions, Registry(values = Map('a' -> 1))).values('h')
  }

  println(lastValue(Day23.instructions(Day23.myInput)))

}
