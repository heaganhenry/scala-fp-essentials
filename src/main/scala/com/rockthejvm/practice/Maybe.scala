package com.rockthejvm.practice

// Create a small collection of AT MOST ONE element called Maybe[A] with implementations for
// - map, flatMap & filter

abstract class Maybe[A] {
  def map[B](transform: A => B): Maybe[B]
  def flatMap[B](transform: A => Maybe[B]): Maybe[B]
  def filter(predicate: A => Boolean): Maybe[A]
  def withFilter(predicate: A => Boolean): Maybe[A] = filter(predicate)
}

case class MaybeNot[A]() extends Maybe[A] {
  override def map[B](transform: A => B): Maybe[B] = MaybeNot[B]()
  override def flatMap[B](transform: A => Maybe[B]): Maybe[B] = MaybeNot[B]()
  override def filter(predicate: A => Boolean): Maybe[A] = this
}

case class Just[A](element: A) extends Maybe[A] {
  override def map[B](transform: A => B): Maybe[B] = Just(transform(element))

  override def flatMap[B](transform: A => Maybe[B]): Maybe[B] = transform(element)

  override def filter(predicate: A => Boolean): Maybe[A] =
    if (predicate(element)) this
    else MaybeNot[A]()
}

object MaybeTest {
  def main(args: Array[String]): Unit = {
    val maybeInt: Maybe[Int] = Just(3)
    val maybeInt2: Maybe[Int] = MaybeNot()
    val maybeIncrementedInt = maybeInt.map(_ + 1)
    val maybeIncrementedInt2 = maybeInt2.map(_ + 1)
    println(maybeIncrementedInt)
    println(maybeIncrementedInt2)

    val maybeFiltered = maybeInt.filter(_ % 2 == 0)
    println(maybeFiltered)

    val maybeFlatMapped = maybeInt.flatMap(number => Just(number * 3))
    println(maybeFlatMapped)
  }
}