package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_MapPartitions {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("mappartitions")
		val sc = new SparkContext(conf)

		val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)
		val mpRdd: RDD[Int] = rdd.mapPartitions(
			iter => {
				println(">>>>>>>>")
				iter.map(_ * 2)
			}
		)

		mpRdd.collect().foreach(println)
		sc.stop()
	}
}
