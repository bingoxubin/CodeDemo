package com.bingo.first

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * HDFS数据源
  */
object HdfsWordCount {

	def main(args: Array[String]): Unit = {
		Logger.getLogger("org").setLevel(Level.ERROR)

		// todo: 1、创建SparkConf对象
		val sparkConf: SparkConf = new SparkConf().setAppName("HdfsWordCount").setMaster("local[2]")

		// todo: 2、创建StreamingContext对象
		val ssc = new StreamingContext(sparkConf, Seconds(2))

		//todo: 3、监控hdfs目录数据
		val textFileStream: DStream[String] = ssc.textFileStream("hdfs://node01:8020/data")


		//todo: 4、对数据进行处理
		val result: DStream[(String, Int)] = textFileStream.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)

		//todo: 5、打印结果
		result.print()


		//todo: 6、开启流式计算
		ssc.start()
		ssc.awaitTermination()


	}
}
