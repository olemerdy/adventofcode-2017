package org.lafeuille.adventofcode.y2017.d18

import org.lafeuille.adventofcode.y2017.d18.Registry.getValue

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
  played: List[BigInt] = Nil,
  recovered: List[BigInt] = Nil,
  instruction: BigInt = 0
)

sealed trait Instruction {
  def update(reg: Registry): Registry
}

object Sound {
  val regex: Regex = "snd ([a-z])".r
}

case class Sound(source: Char) extends Instruction {
  override def update(reg: Registry): Registry =
    reg.copy(
      played = reg.values.getOrElse(source, BigInt(0)) :: reg.played,
      instruction = reg.instruction + 1
    )
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

object Add {
  val regex: Regex = "add ([a-z]) (([a-z])|(\\-?[0-9]+))".r
}

case class Add(target: Char, source: Either[Char, BigInt]) extends Instruction {
  override def update(reg: Registry): Registry =
    reg.copy(
      values = reg.values.updated(target, reg.values.getOrElse(target, BigInt(0)) + getValue(reg.values, source)),
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
      instruction = reg.instruction + 1
    )
}

object Modulo {
  val regex: Regex = "mod ([a-z]) (([a-z])|(\\-?[0-9]+))".r
}

case class Modulo(target: Char, source: Either[Char, BigInt]) extends Instruction {
  override def update(reg: Registry): Registry =
    reg.copy(
      values = reg.values.updated(target, reg.values.getOrElse(target, BigInt(0)) % getValue(reg.values, source)),
      instruction = reg.instruction + 1
    )
}

object Recover {
  val regex: Regex = "rcv ([a-z])".r
}

case class Recover(source: Char) extends Instruction {
  override def update(reg: Registry): Registry =
    if (reg.values.getOrElse(source, 0) == 0 || reg.played.isEmpty)
      reg.copy(instruction = reg.instruction + 1)
    else
      reg.copy(
        recovered = reg.played.head :: reg.recovered,
        instruction = reg.instruction + 1
      )
}

object Jump {
  val regex: Regex = "jgz (([a-z])|(\\-?[0-9]+)) (([a-z])|(\\-?[0-9]+))".r
}

case class Jump(ref: Either[Char, BigInt], offset: Either[Char, BigInt]) extends Instruction {
  override def update(reg: Registry): Registry =
    reg.copy(instruction = reg.instruction + (
      if (getValue(reg.values, ref) <= 0)
        1
      else
        getValue(reg.values, offset)
      )
    )
}

object Day18 {

  def myInput: Source =
    Source.fromURL(getClass.getResource("input.txt"))

  def source(sc: String, si: String): Either[Char, BigInt] =
    if (sc == null)
      Right(si.toInt)
    else
      Left(sc.head)

  def instructions(input: Source): List[Instruction] =
    input.getLines().map {
      case Add.regex(t, _, sc, si) =>
        Add(t.head, source(sc, si))
      case Jump.regex(_, tc, ti, _, sc, si) =>
        Jump(source(tc, ti), source(sc, si))
      case Modulo.regex(t, _, sc, si) =>
        Modulo(t.head, source(sc, si))
      case Multiply.regex(t, _, sc, si) =>
        Multiply(t.head, source(sc, si))
      case Recover.regex(t) =>
        Recover(t.head)
      case Set.regex(t, _, sc, si) =>
        Set(t.head, source(sc, si))
      case Sound.regex(t) =>
        Sound(t.head)
    }.toList

}

object Day18Part1 extends App {

  def firstRecover(instructions: List[Instruction]): BigInt = {
    @tailrec
    def rec(instructions: List[Instruction], reg: Registry): Registry = {
      if (reg.instruction < 0 || reg.instruction >= instructions.length || reg.recovered.nonEmpty)
        reg
      else
        rec(instructions, instructions(reg.instruction.toInt).update(reg))
    }

    val reg = rec(instructions, Registry())
    reg.recovered.last
  }

  println(firstRecover(Day18.instructions(Day18.myInput)))

}
