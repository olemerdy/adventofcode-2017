package org.lafeuille.adventofcode.y2017.d25

import org.lafeuille.adventofcode.y2017.core.RelativeDirection
import org.lafeuille.adventofcode.y2017.core.RelativeDirection.RelativeDirection

import scala.annotation.tailrec

case class Actions(write: Int, move: RelativeDirection, continue: Char) {
  require(write == 0 || write == 1)
  require(move == RelativeDirection.Left || move == RelativeDirection.Right)
}

case class Rule(state: Char, actionsIf0: Actions, actionsIf1: Actions)

case class Input(state: Char, checksumAfter: Int, rules: Set[Rule])

object Day25 {

  def myInput: Input = Input(
    state = 'A',
    checksumAfter = 12861455,
    rules = Set(
      Rule(
        state = 'A',
        Actions(write = 1, move = RelativeDirection.Right, continue = 'B'),
        Actions(write = 0, move = RelativeDirection.Left, continue = 'B')
      ),
      Rule(
        state = 'B',
        Actions(write = 1, move = RelativeDirection.Left, continue = 'C'),
        Actions(write = 0, move = RelativeDirection.Right, continue = 'E')
      ),
      Rule(
        state = 'C',
        Actions(write = 1, move = RelativeDirection.Right, continue = 'E'),
        Actions(write = 0, move = RelativeDirection.Left, continue = 'D')
      ),
      Rule(
        state = 'D',
        Actions(write = 1, move = RelativeDirection.Left, continue = 'A'),
        Actions(write = 1, move = RelativeDirection.Left, continue = 'A')
      ),
      Rule(
        state = 'E',
        Actions(write = 0, move = RelativeDirection.Right, continue = 'A'),
        Actions(write = 0, move = RelativeDirection.Right, continue = 'F')
      ),
      Rule(
        state = 'F',
        Actions(write = 1, move = RelativeDirection.Right, continue = 'E'),
        Actions(write = 1, move = RelativeDirection.Right, continue = 'A')
      )
    )
  )

}

object Day25Part1 extends App {

  def checksum(input: Input): Int = {
    @tailrec
    def rec(tape: Map[Int, Int], cursor: Int, state: Char, steps: Int, rules: Set[Rule]): Int =
      if (steps == 0)
        tape.values.sum
      else {
        val rule = rules.find(_.state == state).get
        val actions = tape.getOrElse(cursor, 0) match {
          case 0 => rule.actionsIf0
          case 1 => rule.actionsIf1
          case _ => throw new AssertionError()
        }
        val inc = actions.move match {
          case RelativeDirection.Right => 1
          case RelativeDirection.Left => -1
          case _ => throw new AssertionError()
        }
        rec(tape.updated(cursor, actions.write), cursor + inc, actions.continue, steps - 1, rules)
      }

    rec(Map(), 0, input.state, input.checksumAfter, input.rules)
  }

  println(checksum(Day25.myInput))

}