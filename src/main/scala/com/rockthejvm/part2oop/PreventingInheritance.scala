package com.rockthejvm.part2oop

object PreventingInheritance {

  class Person(name: String) {
    final def enjoyLife(): Int = 42 // final = cannot be overridden
  }

  class Adult(name: String) extends Person(name) {
    // override def enjoyLife(): Int = 999 // cannot override final method
  }

  final class Animal // cannot be inherited
  // class Cat extends Animal // illegal

  // sealing a type hierarchy = inheritance only permitted inside this file
  class Guitar(nStrings: Int)
  class ElectricGuitar(nStrings: Int) extends Guitar(nStrings)
  class AcousticGuitar extends Guitar(6)

  // no modifier = "not encouraging" inheritance
  // open = specifically marked for extension
  // not mandatory, good practice
  open class ExtensibleGuitar(nStrings: Int)

  def main(args: Array[String]): Unit = {

  }
}