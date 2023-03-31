package com.bingo.first

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.flume.{FlumeUtils, SparkFlumeEvent}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * sparkStreaming整合flume  推模式Push
  */

object SparkStreaming_Flume_Push {
	//newValues 表示当前批次汇总成的(word,1)中相同单词的所有的1
	//runningCount 历史的所有相同key的value总和
	def updateFunction(newValues: Seq[Int], runningCount: Option[Int]): Option[Int] = {
		val newCount = runningCount.getOrElse(0) + newValues.sum
		Some(newCount)
	}

	def main(args: Array[String]): Unit = {
		//配置sparkConf参数
		val sparkConf: SparkConf = new SparkConf().setAppName("SparkStreaming_Flume_Push").setMaster("local[2]")
		//构建sparkContext对象
		val sc: SparkContext = new SparkContext(sparkConf)
		//构建StreamingContext对象，每个批处理的时间间隔
		val scc: StreamingContext = new StreamingContext(sc, Seconds(5))
		//设置日志输出级别
		sc.setLogLevel("WARN")
		//设置检查点目录
		scc.checkpoint("./")
		//flume推数据过来
		// 当前应用程序部署的服务器ip地址，跟flume配置文件保持一致
		val flumeStream: ReceiverInputDStream[SparkFlumeEvent] = FlumeUtils.createStream(scc, "10.20.42.105", 8888, StorageLevel.MEMORY_AND_DISK)
		//获取flume中数据，数据存在event的body中，转化为String
		val lineStream: DStream[String] = flumeStream.map(x => new String(x.event.getBody.array()))
		//实现单词汇总
		val result: DStream[(String, Int)] = lineStream.flatMap(_.split(" ")).map((_, 1)).updateStateByKey(updateFunction)
		result.print()
		scc.start()
		scc.awaitTermination()
	}
}
