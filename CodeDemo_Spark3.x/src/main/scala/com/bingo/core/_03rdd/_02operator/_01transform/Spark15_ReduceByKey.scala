package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark15_ReduceByKey {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("ReduceByKey")
		val sc = new SparkContext(conf)

		val rdd: RDD[(String, Int)] = sc.makeRDD(List(
			("a", 1), ("a", 2), ("a", 3), ("b", 4)
		))

		val newRDD: RDD[(String, Int)] = rdd.reduceByKey(
			(x: Int, y: Int) => {
				println(s"x=${x},y=${y}")
				x + y
			}
		)

		println(newRDD.collect().mkString(","))
		sc.stop()
	}
}
