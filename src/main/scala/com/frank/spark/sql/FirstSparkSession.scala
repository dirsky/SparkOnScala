package com.frank.spark.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object FirstSparkSession {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("My First Spark Session")
      .master("local")
//    .config("spark.sql.warehouse.dir","file:///")
      .getOrCreate()

    // 用于将DataFrames隐式转换成RDD，使df能够使用RDD中的方法
    import spark.implicits._
    val df = spark.read.json("./people.json")
    df.show()
    df.filter($"age" > 5).show()
    df.createOrReplaceTempView("persons")

    spark.sql("SELECT * FROM persons where age > 15").show()

    spark.stop()

  }
}
