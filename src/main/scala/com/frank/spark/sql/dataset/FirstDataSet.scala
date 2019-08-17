package com.frank.spark.sql.dataset

import org.apache.spark.sql.SparkSession

object FirstDataSet {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("My First Spark Session")
      .master("local")
      //    .config("spark.sql.warehouse.dir","file:///")
      .getOrCreate()

    // 用于将DataFrames隐式转换成RDD，使df能够使用RDD中的方法
    import spark.implicits._

    case class Person(name: String, age: Long)

    val primitiveDS = Seq(1, 2, 3).toDS()
    primitiveDS.map(_ + 1).show()


  }
}
