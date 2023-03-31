package com.bingo.operator

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkBroadCast {

	def main(args: Array[String]): Unit = {
		val conf = new SparkConf().setAppName("sparkBroadCast").setMaster("local[4]")
		val sparkContext = new SparkContext(conf)
		//读取文件，将商品文件数据内容作为广播变量
		val productRdd: RDD[String] = sparkContext.textFile("file:///D:\\开课吧课程资料\\15、scala与spark课程资料\\2、spark课程\\spark_day02\\2、数据\\广播变量实战\\pdts.txt")
		//将数据收集起来
		val mapProduct: collection.Map[String, String] = productRdd.map(x => {
			(x.split(",")(0), x)
		}).collectAsMap()
		//开始广播
		val broadCastValue: Broadcast[collection.Map[String, String]] = sparkContext.broadcast(mapProduct)
		//读取订单数据
		val ordersRDD: RDD[String] = sparkContext.textFile("file:///D:\\开课吧课程资料\\15、scala与spark课程资料\\2、spark课程\\spark_day02\\2、数据\\广播变量实战\\orders.txt")
		//订单数据rdd进行拼接商品数据
		val proudctAndOrderRdd: RDD[String] = ordersRDD.mapPartitions(eachPartition => {
			val getBroadCastMap: collection.Map[String, String] = broadCastValue.value
			val finalStr: Iterator[String] = eachPartition.map(eachLine => {
				val ordersGet: Array[String] = eachLine.split(",")
				val getProductStr: String = getBroadCastMap.getOrElse(ordersGet(2), "")
				eachLine + "\t" + getProductStr
			})
			finalStr
		})
		println(proudctAndOrderRdd.collect().toBuffer)
		sparkContext.stop()
	}
}