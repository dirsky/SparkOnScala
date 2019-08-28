package com.frank.spark.wc

import org.apache.spark.{SparkConf, SparkContext}

object WordCountSimple {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("ScalaWordCount")
    val sc = new SparkContext(conf)

    sc.textFile("./words").flatMap(
      line=>line.split(" ")
    ).map(
      word => new Tuple2(word, 1)
    ).reduceByKey(
      (v1,v2) => v1+v2
    ).sortBy(
      tuple=>{tuple._2}, false
    ).foreach(
      println
    )

    sc.stop()
  }
}
