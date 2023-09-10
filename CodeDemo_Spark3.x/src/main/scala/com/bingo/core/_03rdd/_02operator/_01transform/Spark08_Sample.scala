package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark08_Sample {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sample")
		val sc = new SparkContext(conf)

		val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
		val rdds: RDD[Int] = rdd.sample(false, 0.3)
		rdds.collect().mkString(",")
		sc.stop()
	}
}
