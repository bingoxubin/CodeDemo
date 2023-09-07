package com.bingo.core._03rdd._01builder

import org.apache.spark.{SparkConf, SparkContext}

//如何进行分区划分
object Spark01_RDD_Memory_Par1 {

    def main(args: Array[String]): Unit = {

        // TODO 准备环境
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
        //sparkConf.set("spark.default.parallelism", "1")   如果加上，不影响结果，3个分区轮流执行，每次执行一个分区数据
        val sc = new SparkContext(sparkConf)

        // TODO 创建RDD

        // 【1，2】，【3，4】
        //val rdd = sc.makeRDD(List(1,2,3,4), 2)
        // 【1】，【2】，【3，4】
        //val rdd = sc.makeRDD(List(1,2,3,4), 3)
        // 【1】，【2,3】，【4,5】
        val rdd = sc.makeRDD(List(1,2,3,4,5), 3)

        //		def positions(length: Long, numSlices: Int): Iterator[(Int, Int)] = {
        //      (0 until numSlices).iterator.map { i =>
        //        val start = ((i * length) / numSlices).toInt
        //        val end = (((i + 1) * length) / numSlices).toInt
        //        (start, end)
        //      }
        //    }

        // 将处理的数据保存成分区文件
        rdd.saveAsTextFile("output")

        // TODO 关闭环境
        sc.stop()
    }
}
