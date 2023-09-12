package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark16_GroupByKey {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("GroupByKey")
		val sc = new SparkContext(conf)

		val rdd: RDD[(String, Int)] = sc.makeRDD(List(
			("a", 1), ("a", 2), ("a", 3), ("b", 1)
		))

		val newRDD: RDD[(String, Iterable[Int])] = rdd.groupByKey()

		//用map进行记数 统计求和
		//val newRDD2: RDD[(String, Int)] = newRDD.map {
		//	case (key, iter) => (key, iter.sum)
		//}
		newRDD.collect().foreach(println)

		//跟Groupby的区别，返回类型，key是一致的，value有区别，groupby不会把value单独拿出来
		val newRDD1: RDD[(String, Iterable[(String, Int)])] = rdd.groupBy(_._1)
		newRDD1.collect().foreach(println)

		sc.stop()
	}
}
