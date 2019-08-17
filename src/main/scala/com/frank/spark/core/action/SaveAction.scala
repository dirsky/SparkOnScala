package com.frank.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object SaveAction {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local").setAppName("FirstTransFormation")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    println("==== saveAsTextFile文本文件 ====")
    val rdd = sc.makeRDD(Array(("a",1),("a",3),("c",3),("d",5)))
    rdd.saveAsTextFile("./tf")

    val rdd2 = sc.textFile("./tf")
    rdd2.foreach(println)

  }

}
