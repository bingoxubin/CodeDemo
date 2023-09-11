package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark10_Coalesce {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Coalesce")
		val sc = new SparkContext(conf)
		val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 4)

		val coalRDD: RDD[Int] = rdd.coalesce(2)

		//coalRDD.collect().foreach(println)
		coalRDD.saveAsTextFile("output")
		sc.stop()
	}
}
