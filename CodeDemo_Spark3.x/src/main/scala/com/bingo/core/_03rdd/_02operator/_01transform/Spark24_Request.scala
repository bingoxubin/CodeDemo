package com.bingo.core._03rdd._02operator._01transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

//1)数据准备
//agent.log：时间戳，省份，城市，用户，广告，中间字段使用空格分隔。
//2)需求描述
//统计出每一个省份每个广告被点击数量排行的 Top3
//3)需求分析
//4)功能实现
object Spark24_Request {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Request")
		val sc = new SparkContext(conf)

		val rdd: RDD[String] = sc.textFile("datas/agent.log")

		val mapRDD: RDD[((String, String), Int)] = rdd.map(
			data => {
				val datas: Array[String] = data.split(" ")
				((datas(1), datas(4)), 1)
			}
		)

		val reduceRDD: RDD[(String, (String, Int))] = mapRDD.reduceByKey(_ + _).map {
			case ((prv, ad), sum) => {
				(prv, (ad, sum))
			}
		}

		val groupRDD: RDD[(String, Iterable[(String, Int)])] = reduceRDD.groupByKey()

		val resRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues(
			iter => {
				iter.toList.sortBy(_._2)(Ordering.Int.reverse).take(3)
			}
		)

		resRDD.collect().foreach(println)

		sc.stop()
	}
}
