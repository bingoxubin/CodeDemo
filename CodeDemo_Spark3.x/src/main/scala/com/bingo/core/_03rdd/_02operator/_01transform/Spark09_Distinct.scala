package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark09_Distinct {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("distinct")
		val sc = new SparkContext(conf)
		val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6, 7, 2))

		println(rdd.distinct().collect().mkString(","))

		sc.stop()
	}
}
