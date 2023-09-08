package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.{SparkConf, SparkContext}
//map处理后  分区数 分区数据不变
object Spark01_RDD_Operator_Transform_Part {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - map
        val rdd = sc.makeRDD(List(1,2,3,4),2)
        // 【1，2】，【3，4】
        rdd.saveAsTextFile("output")
        val mapRDD = rdd.map(_*2)
        // 【2，4】，【6，8】
        mapRDD.saveAsTextFile("output1")

        sc.stop()

    }
}
