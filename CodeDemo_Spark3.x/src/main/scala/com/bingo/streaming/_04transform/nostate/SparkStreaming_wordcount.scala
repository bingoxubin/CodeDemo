package com.bingo.streaming._04transform.nostate

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

//注意是这种操作是无状态的，每3秒统计一次，只能记下3秒钟时间内的总数，没有办法进行汇总，统计从开始到现在的总数值

//重要：
//无状态数据操作，只对当前的采集周期内的数据进行处理
//在某些场合下，需要保留数据统计结果（状态），实现数据的汇总
object SparkStreaming_wordcount {
	def main(args: Array[String]): Unit = {
		val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming")
		val ssc = new StreamingContext(conf, Seconds(3))

		val datas: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)
		val words: DStream[String] = datas.flatMap(_.split(" "))
		val wordToOne: DStream[(String, Int)] = words.map((_, 1))
		val wordToCount: DStream[(String, Int)] = wordToOne.reduceByKey(_ + _)

		wordToCount.print

		ssc.start()
		ssc.awaitTermination()
	}
}
