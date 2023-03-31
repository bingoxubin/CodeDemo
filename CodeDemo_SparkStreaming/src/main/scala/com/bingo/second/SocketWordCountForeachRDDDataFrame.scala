package com.bingo.second

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}

/**
  * sparkStreaming整合sparksql
  */
object SocketWordCountForeachRDDDataFrame {

	def main(args: Array[String]): Unit = {
		Logger.getLogger("org").setLevel(Level.ERROR)

		// todo: 1、创建SparkConf对象
		val sparkConf: SparkConf = new SparkConf().setAppName("NetworkWordCountForeachRDDDataFrame").setMaster("local[2]")

		// todo: 2、创建StreamingContext对象
		val ssc = new StreamingContext(sparkConf, Seconds(2))

		//todo: 3、接受socket数据
		val socketTextStream: ReceiverInputDStream[String] = ssc.socketTextStream("node01", 9999)

		//todo: 4、对数据进行处理
		val words: DStream[String] = socketTextStream.flatMap(_.split(" "))

		//todo: 5、对DStream进行处理，将RDD转换成DataFrame
		words.foreachRDD(rdd => {

			//获取得到sparkSessin，由于将RDD转换成DataFrame需要用到SparkSession对象
			val sparkSession: SparkSession = SparkSession.builder().config(rdd.sparkContext.getConf).getOrCreate()
			import sparkSession.implicits._
			val dataFrame: DataFrame = rdd.toDF("word")

			//将dataFrame注册成表
			dataFrame.createOrReplaceTempView("words")

			//统计每个单词出现的次数
			val result: DataFrame = sparkSession.sql("select word,count(*) as count from words group by word")

			//展示结果
			result.show()

		})

		//todo: 6、开启流式计算
		ssc.start()
		ssc.awaitTermination()


	}
}
