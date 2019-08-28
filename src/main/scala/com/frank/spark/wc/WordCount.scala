package com.frank.spark.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("ScalaWordCount")
    val sc = new SparkContext(conf)

    val lines: RDD[String] = sc.textFile("./words")
    val words: RDD[String] = lines.flatMap(
      line=>line.split(" ")
    )

    val pairWords:  RDD[(String, Int)] = words.map(
      word => new Tuple2(word, 1)
    )

    val reduce:RDD[(String, Int)] = pairWords.reduceByKey(
      (v1,v2) => v1+v2
      // (v1:Int,v2:Int) => v1+v2
    )

    val result:RDD[(String, Int)] = reduce.sortBy(
      tuple=>{tuple._2}, false
    )

    result.foreach(
      println
    )
    sc.stop()
  }
}
