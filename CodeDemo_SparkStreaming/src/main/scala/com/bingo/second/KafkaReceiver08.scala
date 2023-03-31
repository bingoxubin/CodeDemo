package com.bingo.second

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * sparkStreaming使用kafka 0.8API基于recevier来接受消息
  */
object KafkaReceiver08 {
	def main(args: Array[String]): Unit = {
		Logger.getLogger("org").setLevel(Level.ERROR)

		//1、创建StreamingContext对象
		val sparkConf = new SparkConf()
		  .setAppName("KafkaReceiver08")
		  .setMaster("local[2]")
		  //开启WAL机制
		  .set("spark.streaming.receiver.writeAheadLog.enable", "true")
		val ssc = new StreamingContext(sparkConf, Seconds(2))

		//需要设置checkpoint,将接受到的数据持久化写入到hdfs上
		ssc.checkpoint("hdfs://node01:8020/wal")

		//2、接受kafka数据
		val zkQuorum = "node01:2181,node02:2181,node03:2181"
		val groupid = "KafkaReceiver08"
		val topics = Map("test" -> 1)

		//(String, String) 元组的第一位是消息的key，第二位表示消息的value
		val receiverDstream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc, zkQuorum, groupid, topics)


		//3、获取kafka的topic数据
		val data: DStream[String] = receiverDstream.map(_._2)

		//4、单词计数
		val result: DStream[(String, Int)] = data.flatMap(_.split(" "))
		  .map((_, 1))
		  .reduceByKey(_ + _)

		//5、打印结果
		result.print()

		//6、开启流式计算
		ssc.start()
		ssc.awaitTermination()

	}
}
