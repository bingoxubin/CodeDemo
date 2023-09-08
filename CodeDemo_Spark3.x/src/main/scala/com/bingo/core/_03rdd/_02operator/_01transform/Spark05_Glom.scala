package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark05_Glom {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local").setAppName("Glom")
		val sc = new SparkContext(conf)
		val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

		val glomRDD: RDD[Array[Int]] = rdd.glom()
		val maxRDD: RDD[Int] = glomRDD.map(
			data => {
				data.max
			}
		)
		println(maxRDD.collect().sum)
		//rdd.collect().foreach(println)
		sc.stop()
	}

}
