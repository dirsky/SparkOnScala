package com.frank.spark.core.trans

import org.apache.spark.{SparkConf, SparkContext}

object RddWithRdd {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local").setAppName("FirstTransFormation")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    println("==== union(otherDataset) ====")
    val rdd1 = sc.parallelize(1 to 5)
    val rdd2 = sc.parallelize(3 to 7)
    val rdd3 = rdd1.union(rdd2)
    println("union")
    rdd3.foreach(print)
    println()

    println("subtract把第一个rdd中同时也存在于第二个rdd中的元素减去")
    val rdd4 = rdd1.subtract(rdd2)
    rdd4.foreach(print)
    println()

    println("intersection相同的元素")
    val rdd5 = rdd1.intersection(rdd2)
    rdd5.foreach(print)
    println()

    println("cartesian笛卡尔积，所有的组合")
    val rdd6 = rdd1.cartesian(rdd2)
    rdd6.foreach(print)
    println()


    println("==== join(otherDataset, [numTasks])====")
    val j1Rdd = sc.parallelize(Array((1,"a"),(2,"b"),(3,"c")))
    val j2Rdd = sc.parallelize(Array((1,4),(2,5),(3,6)))
    j1Rdd.join(j2Rdd).foreach(print)
    println()

    println("==== reduceByKey(func, [numTasks]) ====")
    val r1Rdd = sc.parallelize(List(("female",1),("male",5),("female",5),("male",2)))
    r1Rdd.reduceByKey((x,y) => x+y).foreach(println)
    println()

    println("==== groupByKey多步实现reduce ====")
    val words = Array("one", "two", "two", "three", "three", "three")
    val wordPairsRDD = sc.parallelize(words).map(word => (word, 1))
    val group = wordPairsRDD.groupByKey()
    group.foreach(println)
    group.map(t => (t._1, t._2.sum)).foreach(println)


  }

}
