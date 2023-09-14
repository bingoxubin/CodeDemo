package com.bingo.core._03rdd._02operator._02action

import org.apache.spark.{SparkConf, SparkContext}

object Spark03_Fold {
	def main(args: Array[String]): Unit = {
		val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
		val sc = new SparkContext(sparkConf)

		val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)

		val res: Int = rdd.aggregate(10)(_ + _, _ + _)
		val res1: Int = rdd.fold(10)(_ + _)
		println(res)
		println(res1)

		sc.stop()
	}
}
