package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark13_DoubleValue {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("double value")
		val sc = new SparkContext(conf)

		val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
		val rdd2: RDD[Int] = sc.makeRDD(List(3, 4, 5, 6))

		//3,4
		//1,2,3,4,3,4,5,6
		//1,2
		//(1,3),(2,4),(3,5),(4,6)

		//交集
		val rdd3: RDD[Int] = rdd1.intersection(rdd2)
		println(rdd3.collect().mkString(","))

		//并集
		val rdd4: RDD[Int] = rdd1.union(rdd2)
		println(rdd4.collect().mkString(","))

		//差集
		val rdd5: RDD[Int] = rdd1.subtract(rdd2)
		println(rdd5.collect().mkString(","))

		//拉链
		val rdd6: RDD[(Int, Int)] = rdd1.zip(rdd2)
		println(rdd6.collect().mkString(","))

		sc.stop()
	}
}
