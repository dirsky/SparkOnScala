package com.frank.demo

/**
  * 1.scala中定义在object中的变量，方法都是静态的,object叫对象，相当于java中的单例对象。object不可以传参,Trait也不可传参。
  * 2.scala 中一行代码后可以写“;”也可以不写，会有分号推断机制。多行代码写在一行要用分号隔开。
  * 3.定义变量用var,定义常量用val ， a: Int ": Int" 是变量的类型，可以写也可以不写，不写会自动推断变量类型
  * 4.scala中变量，类，对象，方法定义建议符合驼峰命名法。
  * 5.class 是scala中的类,类可以传参，传参就有了默认的构造函数。类中的属性默认就有getter，setter方法。重写构造，第一行要调用默认的构造
  * 6.当new 一个class时，类中除了方法不执行，其他都执行。同一个包下，class的名称不能相同。
  * 7.scala中 如果一个class名称和object的名称一致，那么这个class叫做这个object的伴生类，这个object叫做这个class的伴生对象，他们之间可以互相访问私有变量。
  */
object HelloWorld {
  println("==============object com.frank.demo.HelloWorld=================")
  val score = 100

  def main(args: Array[String]): Unit = {
    val a = 100
    println(a)
    var b = 3
    b = 4
    println(b)
    val c: Double = 15
    println(c)

    val person = new Person("frank", 15)
//    person.age = 30
    println(person.name)
    println(person.age)

    val p1 = new Person("candy", 15, 'f')
    println(p1.name)
    println(p1.age)

    p1.showName()

  }

}

class Person(xname: String, xage: Int) {
  private val me = "this is me"
  val name = xname
  var age = xage
  var gender = 'm'
  println("hi -------------------------------")

  def this(yname: String, yage: Int, ygender: Char) {
    this(yname, yage)
    this.gender = ygender
  }


  def showName() = {
    println("this is showName" + HelloWorld.score + me)
  }
}
