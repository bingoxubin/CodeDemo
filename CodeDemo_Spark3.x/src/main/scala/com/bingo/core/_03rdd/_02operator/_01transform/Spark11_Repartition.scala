package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark11_Repartition {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Repartition")
		val sc = new SparkContext(conf)
		val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 2)

		//如果这么写是没有意义的，分区还是2，不会打乱重组
		//rdd.coalesce(3)
		//需要shuffle
		val newRDD: RDD[Int] = rdd.coalesce(3,true)
		newRDD.saveAsTextFile("output")
		sc.stop()
	}
}
