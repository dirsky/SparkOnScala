package com.frank.spark.core.trans

import org.apache.spark.{SparkConf, SparkContext}

object SecondTransFormation {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local").setAppName("FirstTransFormation")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    println("==== repartition(numPartitions) ====")
    val rdd = sc.parallelize(1 to 16,4)
    println(rdd.partitions.size)

    val rerdd = rdd.repartition(2)
    println(rerdd.partitions.size)

    println("==== sortBy(func,[ascending], [numTasks]) ====")
    val sortRdd = sc.parallelize(List(3,2,1,4))
    sortRdd.sortBy(x => x).foreach(print)
    println()
    // 按照处理后的数据比较结果排序
    // x%3求余数结果为0，2，1，1
    sortRdd.sortBy(x => x%3).foreach(print)
    println()

    println("==== sortByKey([ascending], [numTasks])  ====")
    val sRdd = sc.parallelize(Array((3,"aa"),(6,"cc"),(2,"bb"),(1,"dd")))
    sRdd.sortByKey(true).foreach(println)

  }

}
