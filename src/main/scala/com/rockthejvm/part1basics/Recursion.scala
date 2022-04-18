package com.rockthejvm.part1basics

import scala.annotation.tailrec

object Recursion {

  // "repetition" = recursion
  def sumUntil(n: Int): Int =
    if (n <= 0) n
    else n + sumUntil(n - 1) // "stack" recursion

  def sumUntil_v2(n: Int): Int = {
    /*
      sut(10, 0) =
      sut(9, 0 + 10) =
      sut(8, 10 + 9) =
      sut(7, 10 + 9 + 8) =
      ...
      sut(0, 10 + 9 + 8 + ... + 3 + 2 + 1) = 10 + ... + 3 + 2 + 1 = 55
    */
    @tailrec
    def sumUntilTailrec(x: Int, accumulator: Int): Int =
      if (x <= 0) accumulator
      else sumUntilTailrec(x - 1, accumulator + x) // TAIL recursion = recursive call occurs LAST in its code path
      // no further stack frames necessary = no more risk of Stack Overflow

    sumUntilTailrec(n, 0)
  }

  def sumNumbersBetween(a: Int, b: Int): Int =
    if (a > b) 0
    else a + sumNumbersBetween(a + 1, b)

  def sumNumbersBetween_v2(a: Int, b: Int): Int = {
    @tailrec
    def sumTailrec(current: Int, accumulator: Int): Int =
      if (current > b) accumulator
      else sumTailrec(current + 1, accumulator + current)

    sumTailrec(a, 0)
  }

  /**
   * Exercises
   * 1. Concatenate a string n times
   * 2. Fibonacci function, tail recursive
   * 3. Is isPrime function tail recursive or not?
   */

  // 1
  def concatenate(str: String, n: Int): String = {
    @tailrec
    def concatTailrec(cur: Int, acc: String): String =
      if (cur <= 0) acc
      else concatTailrec(cur - 1, acc + str)

    concatTailrec(n, "")
  }

  // 2
  def fibonacci(n: Int): Int = {
    @tailrec
    def fibTailrec(i: Int, last: Int, prev: Int): Int =
      if (i >= n) last
      else fibTailrec(i + 1, last + prev, last)

    if (n <= 2) 1
    else fibTailrec(2, 1, 1)
  }

  // 3 - yes, refactored for clarity:
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else if (n % t == 0) false
      else isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  def main(args: Array[String]): Unit = {
    println(sumUntil_v2(20000))
    println(sumNumbersBetween_v2(1, 5))
    println(concatenate("hey", 4))
    println(fibonacci(5))
    println(isPrime(7))
  }
}