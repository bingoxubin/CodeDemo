package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_Operator_Transform_Test {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - mapPartitions
        val rdd = sc.makeRDD(List(1,2,3,4), 2)

        // 【1，2】，【3，4】
        // 【2】，【4】
        val mpRDD = rdd.mapPartitions(
            iter => {
                //List(iter.max).iterator
                val max: Int = iter.max
                List(max).iterator
            }
        )
        mpRDD.collect().foreach(println)

        sc.stop()
    }
}
