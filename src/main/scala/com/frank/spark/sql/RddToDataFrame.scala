package com.frank.spark.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object RddToDataFrame {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf();
    conf.setMaster("local").setAppName("people")
    val sc = new SparkContext(conf);
    val people = sc.textFile("./people.txt")
    val p =people.map(_.split(","))
      .map(p => (p(0), p(1).trim.toInt))
    p.foreach(println)

    sc.stop()

  }
}
