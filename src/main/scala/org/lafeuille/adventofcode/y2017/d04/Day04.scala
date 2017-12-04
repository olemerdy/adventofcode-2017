package org.lafeuille.adventofcode.y2017.d04

import scala.io.Source
import scala.io.Source.fromURL

import Day04.{myInput, passphrases}

object Day04 {

  def myInput: Source =
    fromURL(getClass.getResource("input.txt"))

  def passphrases(input: Source): List[String] =
    input.getLines().toList

}

object Day04Part1 extends App {

  def isValid(passphrase: String): Boolean = {
    val words = passphrase.split("\\s+")
    words.length == words.toSet.size
  }

  def countValid(passphrases: List[String]): Int =
    passphrases.count(p => isValid(p))

  println(countValid(passphrases(myInput)))
}