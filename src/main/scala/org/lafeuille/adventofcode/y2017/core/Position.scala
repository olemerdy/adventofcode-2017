package org.lafeuille.adventofcode.y2017.core

import org.lafeuille.adventofcode.y2017.core.CardinalDirection.CardinalDirection
import org.lafeuille.adventofcode.y2017.core.RelativeDirection.RelativeDirection

case class Position(x: Int, y: Int) {

  def neighbors: Seq[Position] =
    for (i <- -1 to 1; j <- -1 to 1)
      yield Position(x + i, y + j)

  def move(to: CardinalDirection): (Position, CardinalDirection) =
    (to match {
      case CardinalDirection.North =>
        copy(y = y + 1)
      case CardinalDirection.East =>
        copy(x = x + 1)
      case CardinalDirection.South =>
        copy(y = y - 1)
      case CardinalDirection.West =>
        copy(x = x - 1)
    }, to)

  def move(to: RelativeDirection, facing: CardinalDirection): (Position, CardinalDirection) =
    move((to, facing) match {
      case (RelativeDirection.Forward, direction) =>
        direction
      case (RelativeDirection.Right, CardinalDirection.North) =>
        CardinalDirection.East
      case (RelativeDirection.Right, CardinalDirection.East) =>
        CardinalDirection.South
      case (RelativeDirection.Right, CardinalDirection.South) =>
        CardinalDirection.West
      case (RelativeDirection.Right, CardinalDirection.West) =>
        CardinalDirection.North
      case (RelativeDirection.Backward, CardinalDirection.North) =>
        CardinalDirection.South
      case (RelativeDirection.Backward, CardinalDirection.East) =>
        CardinalDirection.West
      case (RelativeDirection.Backward, CardinalDirection.South) =>
        CardinalDirection.North
      case (RelativeDirection.Backward, CardinalDirection.West) =>
        CardinalDirection.East
      case (RelativeDirection.Left, CardinalDirection.North) =>
        CardinalDirection.West
      case (RelativeDirection.Left, CardinalDirection.East) =>
        CardinalDirection.North
      case (RelativeDirection.Left, CardinalDirection.South) =>
        CardinalDirection.East
      case (RelativeDirection.Left, CardinalDirection.West) =>
        CardinalDirection.South
      case _ =>
        throw new AssertionError
    })

}

object CardinalDirection extends Enumeration {
  type CardinalDirection = Value
  val North, East, South, West = Value
}

object RelativeDirection extends Enumeration {
  type RelativeDirection = Value
  val Forward, Right, Backward, Left = Value
}
