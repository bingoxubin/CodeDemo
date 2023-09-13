package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark22_LeftOuterJoin {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("LeftOuterJoin")
		val sc = new SparkContext(conf)

		val rdd1 = sc.makeRDD(List(
			("a", 1), ("b", 2) , ("c", 3)
		))

		val rdd2 = sc.makeRDD(List(
			("a", 4), ("b", 5), ("b", 6)
		))

		val newRDD: RDD[(String, (Int, Option[Int]))] = rdd1.leftOuterJoin(rdd2)

		newRDD.collect().foreach(println)

		sc.stop()
	}
}
