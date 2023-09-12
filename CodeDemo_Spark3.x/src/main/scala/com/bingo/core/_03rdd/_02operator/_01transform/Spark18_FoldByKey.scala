package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark18_FoldByKey {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("FoldByKey")
		val sc = new SparkContext(conf)

		val rdd = sc.makeRDD(List(
			("a", 1), ("a", 2), ("b", 3),
			("b", 4), ("b", 5), ("a", 6)
		), 2)

		rdd.foldByKey(0)(_+_).collect().foreach(println)

		sc.stop()
	}
}
