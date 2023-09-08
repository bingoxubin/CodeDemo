package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark04_FlatMap {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local").setAppName("flatmap")
		val sc = new SparkContext(conf)
		val rdd: RDD[List[Int]] = sc.makeRDD(List(List(1, 2), List(3, 4)))

		val flatRDD: RDD[Int] = rdd.flatMap(
			list => {
				list
			}
		)

		//类比
		val rdd1: RDD[String] = sc.makeRDD(List("hello java", "hello scala"))
		val flatRDD1: RDD[String] = rdd1.flatMap(
			s => {
				s.split(" ")
			}
		)

		flatRDD.collect().foreach(println)
		flatRDD1.collect().foreach(println)
		sc.stop()
	}
}
