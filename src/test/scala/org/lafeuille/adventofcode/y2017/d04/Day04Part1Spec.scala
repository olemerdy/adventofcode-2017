package org.lafeuille.adventofcode.y2017.d04

import org.lafeuille.adventofcode.y2017.d04.Day04.{myInput, passphrases}
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.prop.TableDrivenPropertyChecks

class Day04Part1Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "My input with 1st method" should {
    val myResult = 386

    s"have $myResult valid passphrases" in {
      Day04Part1.countValid(passphrases(myInput)) shouldBe myResult
    }
  }

  "Sample input with 1st method" should {

    val passphrases = Table(
      ("Passphrase", "Valid"),
      ("aa bb cc dd ee", true),
      ("aa bb cc dd aa", false),
      ("aa bb cc dd aaa", true)
    )

    forAll(passphrases) { (passphrase, isValid) =>
      s"have passphrase $passphrase be valid: $isValid" in {
        Day04Part1.isValid(passphrase) shouldBe isValid
      }
    }

  }

}
