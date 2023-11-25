package com.bingo.util

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

import java.util.Properties

object MyKafkaUtil {
	//1.创建配置信息对象
	private val properties: Properties = PropertiesUtil.load("config.properties")

	//2.用于初始化链接到集群的地址
	val broker_list: String = properties.getProperty("kafka.broker.list")

	//3.kafka 消费者配置
	val kafkaParam = Map(
		"bootstrap.servers" -> broker_list,
		"key.deserializer" -> classOf[StringDeserializer],
		"value.deserializer" -> classOf[StringDeserializer],

		//消费者组
		"group.id" -> "commerce-consumer-group",

		//如果没有初始化偏移量或者当前的偏移量不存在任何服务器上，可以使用这个配置属性
		//可以使用这个配置，latest 自动重置偏移量为最新的偏移量
		"auto.offset.reset" -> "latest",

		//如果是 true，则这个消费者的偏移量会在后台自动提交,但是 kafka 宕机容易丢失数据
		//如果是 false，会需要手动维护 kafka 偏移量
		"enable.auto.commit" -> (true: java.lang.Boolean)
	)

	// 创建 DStream，返回接收到的输入数据
	// LocationStrategies：根据给定的主题和集群地址创建 consumer
	// LocationStrategies.PreferConsistent：持续的在所有 Executor 之间分配分区
	// ConsumerStrategies：选择如何在 Driver 和 Executor 上创建和配置 Kafka Consumer
	// ConsumerStrategies.Subscribe：订阅一系列主题
	def getKafkaStream(topic: String, ssc: StreamingContext): InputDStream[ConsumerRecord[String, String]] = {
		val dStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](Array(topic), kafkaParam))
		dStream
	}
}
