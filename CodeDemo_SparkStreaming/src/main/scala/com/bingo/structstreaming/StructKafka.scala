package com.bingo.structstreaming

import org.apache.log4j.{Level, Logger}
import java.sql.Timestamp
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.{Dataset, SparkSession}

object StructKafka {

	def main(args: Array[String]): Unit = {
		Logger.getLogger("org").setLevel(Level.ERROR)

		//创建SparkSession
		val spark = SparkSession
		  .builder()
		  .appName("structured_streamingApp").master("local[*]")
		  .getOrCreate()
		//读取kafka内容
		import spark.implicits._
		val df = spark.readStream
		  .format("kafka")
		  .option("kafka.bootstrap.servers", "node01:9092,node02:9092,node03:9092")
		  .option("subscribe", "test")
		  .option("startingOffsets", "latest") //注意流式处理没有endingOffset
		  .option("includeTimestamp", value = true) //输出内容包括时间戳
		  .load()
		val dataSet: Dataset[(String, Timestamp)] =
			df.selectExpr("CAST(value AS STRING)", "CAST(timestamp AS TIMESTAMP)")
			  .as[(String, Timestamp)]
		//对kafka的数据进行处理
		val words = dataSet.map(line => {
			val lineArr = line._1.replace(",,", ",").split(",")
			(lineArr(0), lineArr(1), lineArr(2), line._2)
		}).toDF("primarykey", "starttime", "value", "timestamp")
		import spark.implicits._
		//定义窗口
		import org.apache.spark.sql.functions._
		//根据窗口、开始id分组
		val windowsCount = words
		  //     .withWatermark("timestamp","1 minutes")
		  .groupBy(
			window($"timestamp", "10 seconds", "5 seconds")
			, $"primarykey"
		).agg(avg("value"))
		// 输出到控制台
		val q = windowsCount
		  .writeStream
		  .queryName("kafka_test")
		  .outputMode(OutputMode.Complete())
		  .option("checkpointLocation", "hdfs://node01:8020/structstreaming/checkpoint")
		  .format("console")
		  .start()
		q.awaitTermination()
	}
}
