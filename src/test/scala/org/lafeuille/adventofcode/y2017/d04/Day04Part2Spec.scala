package org.lafeuille.adventofcode.y2017.d04

import org.lafeuille.adventofcode.y2017.d04.Day04.{myInput, passphrases}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day04Part2Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "My input with 1st method" should {
    val myResult = 208

    s"have $myResult valid passphrases" in {
      passphrases(myInput).count(p => Day04Part2.isValid(p)) shouldBe myResult
    }
  }

  "Sample input with 1st method" should {

    val passphrases = Table(
      ("Passphrase", "Valid"),
      ("abcde fghij", true),
      ("abcde xyz ecdab", false),
      ("a ab abc abd abf abj", true),
      ("iiii oiii ooii oooi oooo", true),
      ("oiii ioii iioi iiio", false)
    )

    forAll(passphrases) { (passphrase, isValid) =>
      s"have passphrase $passphrase be valid: $isValid" in {
        Day04Part2.isValid(passphrase) shouldBe isValid
      }
    }

  }
}
