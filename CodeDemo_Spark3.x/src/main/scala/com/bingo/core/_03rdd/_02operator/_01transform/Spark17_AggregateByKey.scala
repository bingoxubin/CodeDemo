package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark17_AggregateByKey {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("aggregatebykey")
		val sc = new SparkContext(conf)

		val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 2), ("c", 3), ("b", 4), ("c", 5), ("c", 6)), 2)

		//分区内求最大值，分区间求和
		val newRDD: RDD[(String, Int)] = rdd.aggregateByKey(0)(
			(x, y) => Math.max(x, y), (x, y) => x + y
		)

		newRDD.collect().foreach(println)
		sc.stop()
	}
}
