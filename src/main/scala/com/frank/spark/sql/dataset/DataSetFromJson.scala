package com.frank.spark.sql.dataset

import org.apache.spark.sql.SparkSession

object DataSetFromJson {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("My First Spark Session")
      .master("local")
      //    .config("spark.sql.warehouse.dir","file:///")
      .getOrCreate()

    // 用于将DataFrames隐式转换成RDD，使df能够使用RDD中的方法
    import spark.implicits._

    val path = "./people.json"
    val peopleDS = spark.read.json(path).as[Person]

    println("==begin-peopleDS.show()==")
    peopleDS.show()
    peopleDS.where("age>10").show()

    println("==peopleDS.map(_.NAME).show()==")
    peopleDS.map(_.NAME).show()

    println("==end==")

    spark.stop()
  }
}


case class Person(ID:BigInt, NAME: String,AGE: BigInt)


