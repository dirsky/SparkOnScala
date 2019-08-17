package com.frank.demo

object Lesson2 {
  def main(args: Array[String]): Unit = {
    val a=100
    if (a>50){
      println("a>50")
    }
    println(1 to 10 )//打印 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    println(1.to(10))//与上面等价，打印 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

    println(1 to (10 ,2))//步长为2，从1开始打印 ，1,3,5,7,9
    println(1.to(10, 2))

    println(1 until 10 ) //不包含最后一个数，打印 1,2,3,4,5,6,7,8,9
    println(1.until(10))//与上面等价

    println(1 until (10 ,3 ))//步长为2，从1开始打印，打印1,4,7


    for( i <- 1 to 10 ){
      println(i)
    }

    //可以分号隔开，写入多个list赋值的变量，构成多层for循环
    //scala中 不能写count++ count-- 只能写count+
    var count = 0;
    for(i <- 1 to 10; j <- 1 until 10){
      println("i="+ i +",	j="+j)
      count += 1
    }
    println(count);

    //可以在for循环中加入条件判断
    for(i<- 1 to 10 ;if (i%2) == 0 ;if (i == 4) ){
      println(i)
    }

    val result = for (i<- 1 to 50 if (i%10==0)) yield i
    println(result)
    result.foreach(
      // 匿名函数
      x=>println(x+10000)
    )

    result.foreach(
      println
    )

  }
}
