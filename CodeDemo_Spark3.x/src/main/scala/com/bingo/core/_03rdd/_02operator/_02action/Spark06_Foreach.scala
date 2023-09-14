package com.bingo.core._03rdd._02operator._02action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark06_Foreach {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Foreach")
		val sc = new SparkContext(conf)

		val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
		rdd.collect().foreach(println)
		println("***********************")
		rdd.foreach(println)

		sc.stop()
	}
}
