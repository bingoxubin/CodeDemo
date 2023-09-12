package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object Spark14_PartitionBy {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("partitionby")
		val sc = new SparkContext(conf)

		val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
		val mapRDD: RDD[(Int, Int)] = rdd.map(data => (data, 1))

		val newRDD: RDD[(Int, Int)] = mapRDD.partitionBy(new HashPartitioner(2))
		newRDD
		newRDD.saveAsTextFile("output")

		sc.stop()
	}
}
