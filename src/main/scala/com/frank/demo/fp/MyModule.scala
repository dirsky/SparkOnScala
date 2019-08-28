package com.frank.demo.fp

/**
  * This is 文档说明
  * fp
  * 函数式编程
  */

/*
声明一个单例对象（也成为模块），即同时声明一个类和它的唯一实例
我们称以def定义的函数为方法
  */
object MyModule {
  // Unit类似void
  def main(args: Array[String]): Unit = {
    println(formatAbs(-3))
    println(sum(3, 4))

    println(formatFactorial(2))
    println(formatFactorial(7))
    println("=======================")
    println(formatResult("abs",-3,abs))
    println(formatResult("abs",7,factorial))

  }

  // 私有函数
  private def formatAbs(x:Int):String={
    val msg = "The absolute value of %d is %d ."
    // 字符串格式化
    msg.format(x, abs(x))
  }

  // 私有函数
  private def formatFactorial(x:Int):String={
    val msg = "The factorial value of %d is %d ."
    // 字符串格式化
    msg.format(x, factorial(x))
  }

  /*
  高阶函数HOF
  将formatAbs和formatFactorial合并
  传递 f: Int=>Int
  自我理解：像JAVA的策略者模式
   */

  def formatResult(name:String, n:Int, f: Int=>Int)={
    val msg = "The %s value of %d is %d ."
    // 字符串格式化
    msg.format(name, n, f(n))
  }


  // 求绝对值
  def abs(num: Int):Int = {
    if (num < 0)
      -num
    else
      num
  }

  def sum(x:Int,y:Int)={
    // scala没有操作符的概念
    // 实际是调用x的方法x.+(y)
    x+y
  }



  /*
  求阶乘
   */
  def factorial(n:Int)={
    def go(n:Int, acc:Int):Int={
      if (n<=0) acc
      else go(n-1,n*acc)
    }
    go(n,1)
  }
}
