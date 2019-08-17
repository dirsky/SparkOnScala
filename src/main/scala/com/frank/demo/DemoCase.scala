package com.frank.demo

case class Student(name:String, age:Int)

object DemoCase {
  def main(args: Array[String]): Unit = {
    val person1 = new Student("frank",15)
    val person2 = new Student("candy",13)
    val person3 = new Student("doy",1)

    val list = List(person1, person2, person3)
    list.foreach(
      _ match {
        case Student("frank",15) => println("frank")
        case Student("candy",13) => println("candy")
        case _ => println("no match")
      }
    )
  }
}