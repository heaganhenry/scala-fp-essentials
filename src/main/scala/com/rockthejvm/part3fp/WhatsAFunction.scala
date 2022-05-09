package com.rockthejvm.part3fp

object WhatsAFunction {

 // FP: functions are "first-class" citizens
 // JVM was built for Java, an OO language: objects (instances of classes) are "first-class" citizens

 // solution: traits with apply methods
 trait MyFunction[A, B] {
   def apply(arg: A): B
 }

 val doubler = new MyFunction[Int, Int] {
   override def apply(arg: Int): Int = arg * 2
 }

 val meaningOfLife = 42
 val meaningDoubled = doubler(meaningOfLife) // doubler.apply(meaningOfLife)

 // built-in function types
 // Function1[ArgType, ResultType]
 val doublerStandard = new Function1[Int, Int] {
   override def apply(arg: Int): Int = arg * 2
 }

 val meaningDoubled_v2 = doublerStandard(meaningOfLife)

 // Function2[Arg1Type, Arg2Type, ResultType]
 val adder = new Function2[Int, Int, Int] {
   override def apply(a: Int, b: Int): Int = a + b
 }
 val anAddition = adder(2, 67)

 // (Int, String, Double, Boolean) => Int ==== Function4[Int, String, Double, Boolean, Int]
 val aThreeArgFunction = new Function4[Int, String, Double, Boolean, Int] {
   override def apply(v1: Int, v2: String, v3: Double, v4: Boolean): Int = ???
 }

 // all functions are instances of FunctionN with apply methods

  /**
   * Exercises
   * 1. A function which takes 2 strings and concatenates them
   * 2. Replace Predicate/Transformer with the appropriate function types if necessary
   * 3. Define a function which takes an Int as argument and returns ANOTHER function as a result.
   */

  // 1
  val addStrings: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }

  // 2
  // yes: Predicate[T] equivalent with Function1[T, Boolean] === T => Boolean
  // yes: Transformer[A, B] equivalent with Function1[A, B] === (A, B) => B

  // 3
  val superAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int) = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder2 = superAdder(2)
  val anAddition_v2 = adder2(67) // 69
  // currying
  val anAddition_v3 = superAdder(2)(67)

  // function values != methods
  // function values == instances of FunctionN

  def main(args: Array[String]): Unit = {
    println(addStrings("I love ", "Scala"))
  }
}