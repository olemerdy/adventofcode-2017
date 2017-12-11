package org.lafeuille.adventofcode.y2017.d07

import org.lafeuille.adventofcode.y2017.d07.Day07.getClass
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.prop.TableDrivenPropertyChecks

import scala.io.Source.fromURL

class Day07Part1Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  val rootInput = "fwft (72) -> ktlj, cntj, xhth"
  s"Root input '$rootInput'" should {
    val rootInfo = NodeInfo("fwft", 72, List("ktlj", "cntj", "xhth"))

    s"parse as '$rootInfo'" in {
      Day07.parse(rootInput) shouldBe rootInfo
    }
  }

  val leafInput = "pbga (66)"
  s"Leaf input '$leafInput'" should {
    val leafInfo = NodeInfo("pbga", 66, Nil)

    s"parse as '$leafInfo'" in {
      Day07.parse(leafInput) shouldBe leafInfo
    }
  }

  "Sample input" should {
    "have root" in {
      val nodes = Day07.nodeInfos(fromURL(getClass.getResource("sample-input.txt")))
      Day07Part1.findRoot(nodes).name shouldBe "tknk"
    }
  }

  "My input" should {
    "have root" in {
      Day07Part1.findRoot(Day07.nodeInfos(Day07.myInput)).name shouldBe "cqmvs"
    }
  }
}
