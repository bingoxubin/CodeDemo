package com.bingo.core.wc

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
	def main(args: Array[String]): Unit = {
		//经典写法
		//val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
		//val sc = new SparkContext(sparkConf)
		//sc.textFile("datas").flatMap(_.split(" ")).map((_,1)).reduceByKey((_+_)).collect().foreach(println)
		//sc.stop()

		//group by写法
		val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
		val sc = new SparkContext(sparkConf)
		sc.textFile("datas").flatMap(_.split(" ")).groupBy(word => word).map{case(word,list) => (word,list.size)}.collect().foreach(println)
		sc.stop()
	}
}
