package com.bingoabin.datastream.sink

import com.bingoabin.util.PropertiesUtil
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.fs.bucketing.{BucketingSink, DateTimeBucketer}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

import java.time.ZoneId
import java.util.Properties

object HDFS {
	def main(args: Array[String]): Unit = {
		val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

		val bootstrapServers: String = PropertiesUtil.getStringByKey("bootstrap.servers", "kafka.properties")
		val topicName: String = PropertiesUtil.getStringByKey("topic", "kafka.properties")
		val groupID: String = PropertiesUtil.getStringByKey("group.id", "kafka.properties")

		val properties = new Properties()
		properties.setProperty("bootstrap.servers", bootstrapServers)
		properties.setProperty("topic.name", topicName)
		properties.setProperty("group.id", groupID)
		properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
		properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

		val kafkaConsumer: FlinkKafkaConsumer[String] = new FlinkKafkaConsumer[String](topicName, new SimpleStringSchema(), properties)
		kafkaConsumer.setStartFromLatest()

		val sourceData: DataStream[String] = env.addSource(kafkaConsumer)
		sourceData.print()

		val outputPath = PropertiesUtil.getStringByKey("hdfsPath", "hdfs.properties")

		val hdfsSink: BucketingSink[String] = new BucketingSink[String](outputPath)
		//使用东八区时间格式"yyyy-MM-dd"命名存储区
		hdfsSink.setBucketer(new DateTimeBucketer[String]("yyyy-MM-dd", ZoneId.of("Asia/Shanghai")))
		// 下述两种条件满足其一时，创建新的块文件
		// 条件1.设置块大小为100MB
		hdfsSink.setBatchSize(100 * 1024 * 1024) // this is 100 MB
		// 条件2.设置时间间隔10s
		hdfsSink.setBatchRolloverInterval(10 * 1000) // this is 10s
		// 设置块文件前缀
		hdfsSink.setPendingPrefix("")
		// 设置块文件后缀
		hdfsSink.setPendingSuffix("")
		// 设置运行中的文件前缀
		hdfsSink.setInProgressPrefix(".")

		//添加sink
		sourceData.addSink(hdfsSink)

		env.execute("FlinkHDFSSink")
	}
}
