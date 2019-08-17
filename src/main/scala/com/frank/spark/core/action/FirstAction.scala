package com.frank.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object FirstAction {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local").setAppName("FirstTransFormation")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    println("==== reduce(func) ====")
    val rRdd = sc.makeRDD(1 to 10, 2)
    println("rRdd.count():"+rRdd.count())
    val r1 = rRdd.reduce(_+_)
    println(r1)

    val rdd2 = sc.makeRDD(Array(("a",1),("a",3),("c",3),("d",5)))
    val r2 = rdd2.reduce((x,y)=>(x._1 + y._1,x._2 + y._2))
    println("rdd2.count()"+rdd2.count())
    println(r2)

    println(rdd2.first())
    println(rdd2.take(2))

    println("==== countByKey() ====")
    val cRdd = sc.parallelize(List((1,3),(1,2),(1,4),(2,3),(3,6),(3,8)),3)
    cRdd.countByKey().foreach(println)

    println("==== foreach(func) ====")
    val fRdd = sc.makeRDD(1 to 10,2)
    var sum = sc.accumulator(0)
    fRdd.foreach(sum += _)
    println(sum.value)


  }

}
