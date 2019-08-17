package com.frank.spark.core.trans

import org.apache.spark.{SparkConf, SparkContext}

object FirstTransFormation {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local").setAppName("FirstTransFormation")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    println("==== map(func) ====")
    val source = sc.parallelize(1 to 5)
    source.collect().foreach(print)
    println("-- collect()用来触发 --")
    source.map(_ * 2).collect().foreach(print)

    println()
    println("==== mapPartitions(func) ====")
    val rdd = sc.parallelize(
        // 四个分区
        List(("kpop","female"), ("zorro","male"),
          ("mobin","male"), ("lucy","female"))
    )

    // mapPartitions执行4次，和分区数量一样
    val result = rdd.mapPartitions(partitionsFun)
    result.collect().foreach(println)

    println("==== glom ====")
    val rdd2 = sc.parallelize(1 to 6,3)
    rdd2.foreach(println)
    rdd2.glom().collect().foreach(println)

    println("==== flatMap(func) map ====")
    val sourceFlat = sc.parallelize(1 to 5)
    sourceFlat.collect()
    sourceFlat.foreach(print)
    println()

    // 每一个输入元素可以被映射为0或多个输出元素
    val flatMap = sourceFlat.flatMap(1 to _)
    flatMap.foreach(print)
    println()

    println("==== filter(func) ====")
    val sourceFilter = sc.parallelize(
        Array("xiaoming", "xiaojiang", "xiaohe", "dazhi"))

    val filter = sourceFilter.filter(_.contains("xiao"))
    filter.collect().foreach(println)


    println("==== distinct([numTasks]) ====")
    val distinctRdd = sc.parallelize(List(1,2,1,5,2,9,6,1))
    // 默认情况下，只有8个并行任务来操作，但是可以传入一个可选的numTasks参数改变它。
    distinctRdd.distinct().foreach(print)
    println()

    println("==== sample(withReplacement, fraction, seed) ====")
    val sampleRdd = sc.parallelize(1 to 10)
    val sr1 = sampleRdd.sample(true,0.2)
    sr1.foreach(print)
    println()
    println()
    val sr2 = sampleRdd.sample(true,0.8)
    sr2.foreach(print)
    println()
  }


  def partitionsFun(iter : Iterator[(String,String)]) : Iterator[String] = {
    // 分区结果
    var woman = List[String]()
    while (iter.hasNext){
      val next = iter.next()
      next match {
          // 做过滤
        case (_,"female") => woman = next._1 :: woman
        case _ =>
      }
    }
    woman.iterator
  }


}
