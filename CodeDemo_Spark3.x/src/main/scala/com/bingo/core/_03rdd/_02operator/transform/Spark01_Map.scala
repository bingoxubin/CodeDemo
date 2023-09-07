package com.bingo.core._03rdd._02operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Map {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local").setAppName("map")
		val sc = new SparkContext(conf)
		val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5))

		def mapFunctions(num:Int): Int = {
			num * 2
		}

		//val mapRDD: RDD[Int] = rdd.map(mapFunction)
		//val mapRDD: RDD[Int] = rdd.map((num:Int)=>{num*2})
		//val mapRDD: RDD[Int] = rdd.map((num:Int)=>num*2)
		//val mapRDD: RDD[Int] = rdd.map((num)=>num*2)
		//val mapRDD: RDD[Int] = rdd.map(num=>num*2)
		val mapRDD: RDD[Int] = rdd.map(_ * 2)

		mapRDD.collect().foreach(println)
		sc.stop()
	}
}
