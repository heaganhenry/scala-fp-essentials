package com.rockthejvm.practice

import scala.annotation.tailrec

// singly linked list
// [1, 2, 3] = [1] -> [2] -> [3] -> |
abstract class LList {
  def head: Int
  def tail: LList
  def isEmpty: Boolean
  def add(element: Int): LList = new Cons(element, this)
}

class Empty extends LList {
  override def head: Int = throw new NoSuchElementException
  override def tail: LList = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def toString: String = "[]"
}

class Cons(override val head: Int, override val tail: LList) extends LList {
  override def isEmpty: Boolean = false
  override def toString: String = {
    @tailrec
    def concatenateElements(remainder: LList, acc: String): String =
      if (remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

    s"[${concatenateElements(tail, s"$head")}]"
  }
}

object LListTest {
  def main(args: Array[String]): Unit = {
    val empty = new Empty
    println(empty.isEmpty)
    println(empty)

    val first3Numbers = new Cons(1, new Cons(2, new Cons(3, empty)))
    println(first3Numbers)
  }
}