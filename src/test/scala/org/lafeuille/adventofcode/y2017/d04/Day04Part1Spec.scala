package org.lafeuille.adventofcode.y2017.d04

import org.lafeuille.adventofcode.y2017.d04.Day04.{myInput, passphrases}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class Day04Part1Spec extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "My input with 1st method" should {
    val myResult = 386

    s"have $myResult valid passphrases" in {
      passphrases(myInput).count(p => Day04Part1.isValid(p)) shouldBe myResult
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
      val validTxt = if (isValid) "valid" else "invalid"
      s"have passphrase \'$passphrase\' be $validTxt" in {
        Day04Part1.isValid(passphrase) shouldBe isValid
      }
    }

  }

}
