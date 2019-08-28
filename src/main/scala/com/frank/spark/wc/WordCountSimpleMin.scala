package com.frank.spark.wc

import org.apache.spark.{SparkConf, SparkContext}

object WordCountSimpleMin {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("ScalaWordCount")
    val sc = new SparkContext(conf)

    sc.textFile("./words").flatMap(
      _.split(" ")
    ).map(
      (_, 1)
    ).reduceByKey(
      _+_
    ).sortBy(
      _._2,false
    ).foreach(
      println
    )

    sc.stop()
  }
}
