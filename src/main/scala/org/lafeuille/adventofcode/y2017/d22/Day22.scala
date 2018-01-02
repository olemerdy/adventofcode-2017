package org.lafeuille.adventofcode.y2017.d22

import org.lafeuille.adventofcode.y2017.core.CardinalDirection.CardinalDirection
import org.lafeuille.adventofcode.y2017.core.RelativeDirection.RelativeDirection
import org.lafeuille.adventofcode.y2017.core.{CardinalDirection, Position, RelativeDirection}

import scala.annotation.tailrec
import scala.io.Source

sealed trait State {
  def turn: RelativeDirection

  def next: State
}

object Clean1 extends State {
  override lazy val turn: RelativeDirection = RelativeDirection.Left

  override def next: State = Infected1
}

object Infected1 extends State {
  override lazy val turn: RelativeDirection = RelativeDirection.Right

  override def next: State = Clean1
}

object Clean2 extends State {
  override lazy val turn: RelativeDirection = RelativeDirection.Left

  override def next: State = Weakened2
}

object Weakened2 extends State {
  override lazy val turn: RelativeDirection = RelativeDirection.Forward

  override def next: State = Infected2
}

object Infected2 extends State {
  override lazy val turn: RelativeDirection = RelativeDirection.Right

  override def next: State = Flagged2
}

object Flagged2 extends State {
  override lazy val turn: RelativeDirection = RelativeDirection.Backward

  override def next: State = Clean2
}

case class VirusCarrier
(
  grid: Map[Position, State],
  current: Position = Position(0, 0),
  facing: CardinalDirection = CardinalDirection.North
)

object Day22 {

  def myInput: Source =
    Source.fromURL(getClass.getResource("input.txt"))

  def grid(input: Source, clean: State, infected: State): Map[Position, State] = {
    for (
      (line, y) <- input.getLines().zipWithIndex;
      fix = line.length / 2;
      (cell, x) <- line.zipWithIndex
    ) yield Position(x - fix, fix - y) -> (if (cell == '#') infected else clean)
  }.toMap

  def bursts(grid: Map[Position, State], steps: Int, defaultState: State): Int = {
    @tailrec
    def rec(virus: VirusCarrier, bursts: Int, steps: Int): Int =
      if (steps <= 0)
        bursts
      else {
        val infected = virus.grid.getOrElse(virus.current, defaultState)
        val (newPos, newFacing) = virus.current.move(infected.turn, virus.facing)
        rec(
          virus.copy(
            virus.grid.updated(virus.current, infected.next),
            current = newPos,
            facing = newFacing
          ),
          bursts = bursts + (infected.next match {
            case Infected1 => 1
            case Infected2 => 1
            case _ => 0
          }),
          steps = steps - 1
        )
      }

    rec(VirusCarrier(grid), 0, steps)
  }

}

object Day22Part1 extends App {

  def grid(input: Source): Map[Position, State] =
    Day22.grid(input, Clean1, Infected1)

  def bursts(grid: Map[Position, State], steps: Int): Int =
    Day22.bursts(grid, steps, Clean1)

  println(bursts(grid(Day22.myInput), 10000))

}

object Day22Part2 extends App {

  def grid(input: Source): Map[Position, State] =
    Day22.grid(input, Clean2, Infected2)

  def bursts(grid: Map[Position, State], steps: Int): Int =
    Day22.bursts(grid, steps, Clean2)

  println(bursts(grid(Day22.myInput), 10000000))

}