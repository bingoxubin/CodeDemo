package com.bingoabin.datastream.sink

import java.util.Properties
import com.alibaba.fastjson.JSON
import com.bingoabin.util.PropertiesUtil
import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink
import org.apache.flink.streaming.connectors.kafka._
import org.apache.flink.streaming.util.serialization.SimpleStringSchema
import org.apache.http.HttpHost
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.Requests

object ES {
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

		val httpHosts = new java.util.ArrayList[HttpHost]
		httpHosts.add(new HttpHost("192.168.214.101", 9200, "http"))

		val esSinkBuilder = new ElasticsearchSink.Builder[String](httpHosts, new ElasticsearchSinkFunction[String] {
			def createIndexRequest(element: String): IndexRequest = {
				val jsonvalue = JSON.parseObject(element)
				return Requests.indexRequest()
				  .index("person")
				  .`type`("person")
				  .source(jsonvalue)
			}

			override def process(element: String, runtimeContext: RuntimeContext, requestIndexer: RequestIndexer): Unit = {
				requestIndexer.add(createIndexRequest(element))
			}
		}
		)

		//批量请求的配置；这将指示接收器在每个元素之后发出请求，否则将对它们进行缓冲。
		esSinkBuilder.setBulkFlushMaxActions(1)

		sourceData.addSink(esSinkBuilder.build())

		env.execute("FlinkESSink")
	}
}
