package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark12_SortBy {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SortBy")
		val sc = new SparkContext(conf)

		val rdd: RDD[Int] = sc.parallelize(Seq(5,2,8,1,3,7,6,4), 2)
		val newRDD: RDD[Int] = rdd.sortBy(num => num)

		newRDD.saveAsTextFile("output")
		//newRDD.collect().foreach(println)
		//newRDD.glom().collect().foreach(data => println(data.mkString(",")))
		sc.stop()
	}
}
