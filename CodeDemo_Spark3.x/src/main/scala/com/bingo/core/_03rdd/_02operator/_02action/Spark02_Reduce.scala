package com.bingo.core._03rdd._02operator._02action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Reduce {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Reduce")
		val sc = new SparkContext(conf)

		val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5))

		//reduce
		//val res: Int = rdd.reduce((x, y) => x + y)
		//println(res)

		//collect:采集
		//方法会将不同分区的数据按照分区顺序采集到Driver端内存中，形成数组
		//val res: Array[Int] = rdd.collect()
		//println(res.mkString(","))

		//count 数据源中数据的个数
		//val count: Long = rdd.count()
		//println(count)

		// first : 获取数据源中数据的第一个
		val first = rdd.first()
		println(first)

		// take : 获取N个数据
		val ints: Array[Int] = rdd.take(3)
		println(ints.mkString(","))

		// takeOrdered : 数据排序后，取N个数据
		val rdd1 = sc.makeRDD(List(4, 2, 3, 1))
		val ints1: Array[Int] = rdd1.takeOrdered(3)
		println(ints1.mkString(","))
		sc.stop()
	}
}
