package com.frank.spark.core.persist

import org.apache.spark.{SparkConf, SparkContext}

object PersistRDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local").setAppName("FirstTransFormation")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    println("==== PersistRDD ====")
    val rdd = sc.makeRDD(1 to 10)

    val nocache = rdd.map(_.toString+"["+System.currentTimeMillis+"]")
    val cache   = rdd.map(_.toString+"["+System.currentTimeMillis+"]")
    cache.cache
    nocache.foreach(println)
    cache.foreach(println)

  }

}
