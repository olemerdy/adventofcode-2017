package org.lafeuille.adventofcode.y2017.d09

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day09Part1Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  val table = Table(
    ("Stream", "Groups", "Score"),
    ("{}", 1, 1),
    ("{{{}}}", 3, 6),
    ("{{},{}}", 3, 5),
    ("{{{},{},{{}}}}", 6, 16),
    ("{<{},{},{{}}>}", 1, -1),
    ("{<a>,<a>,<a>,<a>}", 1, 1),
    ("{{<a>},{<a>},{<a>},{<a>}}", 5, -1),
    ("{{<!>},{<!>},{<!>},{<a>}}", 2, -1),
    ("{{<ab>},{<ab>},{<ab>},{<ab>}}", -1, 9),
    ("{{<!!>},{<!!>},{<!!>},{<!!>}}", -1, 9),
    ("{{<a!>},{<a!>},{<a!>},{<ab>}}", -1, 3)
  )

  forAll(table) { (stream, groups, score) =>
    s"Sample stream '$stream" should {
      if (groups >= 0) {
        s"have groups be $groups" in {
          Day09Part1.groups(stream) shouldBe groups
        }
      }
      if (score >= 0) {
        s"have score be $score" in {
          Day09Part1.score(stream) shouldBe score
        }
      }
    }
  }

}
