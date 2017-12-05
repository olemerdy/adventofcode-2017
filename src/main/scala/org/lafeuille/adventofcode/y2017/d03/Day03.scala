package org.lafeuille.adventofcode.y2017.d03

import org.lafeuille.adventofcode.y2017.core.CardinalDirection.CardinalDirection
import org.lafeuille.adventofcode.y2017.core.RelativeDirection.RelativeDirection
import org.lafeuille.adventofcode.y2017.core.{CardinalDirection, Position, RelativeDirection}

import scala.language.implicitConversions

object Day03 {

  val myInput = 347991

}

object Spiral {

  def initial(value: Int, pos: Position, f: (Spiral, Position, Int) => Int) =
    Spiral(value, Pos(pos, CardinalDirection.South), Map(value -> pos), Map(pos -> value), f)

  def target(initialValue: Int, initialPos: Position)(goal: Int)(f: (Spiral, Position, Int) => Int): Spiral = {
    var spiral = Spiral.initial(initialValue, initialPos, f)
    while (spiral.currentValue < goal) {
      spiral = spiral.next
    }
    spiral
  }

}

object Pos {

  implicit def convert(pos: (Position, CardinalDirection)): Pos =
    Pos(pos._1, pos._2)

  def distance(pos1: Position, pos2: Position = Position(0, 0)): Int =
    Math.abs(pos1.x - pos2.x) + Math.abs(pos1.y - pos2.y)
}

case class Pos(value: Position, facing: CardinalDirection) {

  def move(direction: RelativeDirection): Pos = {
    val (nextPos, nextFacing) = value.move(to = direction, facing = facing)
    Pos(nextPos, nextFacing)
  }

}

case class Spiral(currentValue: Int,
                  currentPos: Pos,
                  valueToPos: Map[Int, Position],
                  posToValue: Map[Position, Int],
                  f: (Spiral, Position, Int) => Int) {

  def pos(value: Int): Option[Position] =
    valueToPos.get(value)

  def value(pos: Position): Option[Int] =
    posToValue.get(pos)

  def hasValue(pos: Position): Boolean =
    value(pos).isDefined

  def next: Spiral = {
    val leftPos = currentPos.move(RelativeDirection.Left)
    val nextPos =
      if (hasValue(leftPos.value))
        currentPos.move(RelativeDirection.Forward)
      else
        leftPos
    val nextValue = f(this, nextPos.value, currentValue)

    copy(
      nextValue,
      nextPos,
      valueToPos.updated(nextValue, nextPos.value),
      posToValue.updated(nextPos.value, nextValue)
    )
  }

}

object Day03Part1 extends App {

  def moves(square: Int): Int = {
    val spiral = Spiral.target(1, Position(0, 0))(square)((_, _, value) => value + 1)
    Pos.distance(spiral.valueToPos(square))
  }

  println(moves(Day03.myInput))
}

object Day03Part2 extends App {

  def spiral(square: Int): Spiral =
    Spiral.target(1, Position(0, 0))(square) { (spiral, position, _) =>
      position.neighbors
        .map(p => spiral.value(p).getOrElse(0))
        .sum
    }

  def firstValueGreaterThan(square: Int): Int =
    spiral(square).currentValue

  println(firstValueGreaterThan(Day03.myInput))

}
