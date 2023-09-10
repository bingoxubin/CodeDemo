package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import java.text.SimpleDateFormat
import java.util.Date

object Spark06_GroupBy {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("groupby")
		val sc = new SparkContext(conf)

		//功能一：简单分组
		//val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)
		//
		//def groupFunction(num:Int): Int = {
		//	num % 2
		//}
		//
		//val groupRDD: RDD[(Int, Iterable[Int])] = rdd.groupBy(groupFunction)
		//
		//groupRDD.collect().foreach(println)

		//功能二：获取每个时间段访问量
		val rdd: RDD[String] = sc.textFile("datas/apache.log")
		val timeRDD: RDD[(String, Iterable[(String, Int)])] = rdd.map(
			lines => {
				val datas: Array[String] = lines.split(" ")
				val time: String = datas(3)
				val sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss")
				val date: Date = sdf.parse(time)
				val sdf1 = new SimpleDateFormat("HH")
				val hour: String = sdf1.format(date)
				(hour, 1)
			}
		).groupBy(_._1)

		timeRDD.map {
			case (hour, iter) => {
				(hour,iter.size)
			}
		}.collect().foreach(println)

		sc.stop()
	}
}
