package com.bingoabin.datastream.source
import java.util.Properties

import com.bingoabin.util.PropertiesUtil
import org.apache.flink.api.scala._
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
object Kafka {
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

		env.execute("FlinkKafkaSource")
	}
}
