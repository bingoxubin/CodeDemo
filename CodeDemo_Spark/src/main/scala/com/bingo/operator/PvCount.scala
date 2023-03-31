package com.bingo.operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object PvCount {
	def main(args: Array[String]): Unit = {
		//1、创建SparkConf对象
		val sparkConf: SparkConf = new SparkConf().setAppName("PV").setMaster("local[2]")
		//2、创建SparkContext对象
		val sc = new SparkContext(sparkConf)
		sc.setLogLevel("warn")
		//3、读取数据文件
		val dataRDD: RDD[String] = sc.textFile("D:\\开课吧课程资料\\15、scala与spark课程资料\\2、spark课程\\spark_day02\\2、数据\\access.log")
		//4、统计PV
		val pv: Long = dataRDD.count()
		println(s"pv:$pv")
		//5、关闭sc
		sc.stop()
	}
}
