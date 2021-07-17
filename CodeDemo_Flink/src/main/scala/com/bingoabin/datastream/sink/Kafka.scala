package com.bingoabin.datastream.sink

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer
import org.apache.flink.streaming.connectors.kafka.internals.KeyedSerializationSchemaWrapper
import org.apache.flink.streaming.util.serialization.SimpleStringSchema

import java.util.Properties

object Kafka {
	def main(args: Array[String]): Unit = {
		val env = StreamExecutionEnvironment.getExecutionEnvironment
		//隐式转换

		//checkpoint配置
		//env.enableCheckpointing(5000);
		//env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
		//env.getCheckpointConfig.setMinPauseBetweenCheckpoints(500);
		//env.getCheckpointConfig.setCheckpointTimeout(60000);
		//env.getCheckpointConfig.setMaxConcurrentCheckpoints(1);
		//env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
		////设置statebackend
		//env.setStateBackend(new RocksDBStateBackend("hdfs://node01:8020/flink_kafka_sink/checkpoints", true));
		val socketStream = env.socketTextStream("localhost", 9999)
		val topic = "test"
		val prop = new Properties()
		prop.setProperty("bootstrap.servers", "node01:9092,node02:9092,node03:9092")
		prop.setProperty("group.id", "test")
		//第一种解决方案，设置FlinkKafkaProducer里面的事务超时时间
		//设置事务超时时间
		prop.setProperty("transaction.timeout.ms", 60000 * 15 + "");

		//第二种解决方案，设置kafka的最大事务超时时间
		//FlinkKafkaProducer011<String> myProducer = new FlinkKafkaProducer<>(brokerList, topic, new SimpleStringSchema());

		//使用支持仅一次语义的形式
		/**
		 * defaultTopic: String,
		 * serializationSchema: KafkaSerializationSchema[IN],
		 * producerConfig: Properties,
		 * semantic: FlinkKafkaProducer.Semantic
		 */
		val kafkaSink = new FlinkKafkaProducer[String](topic, new KeyedSerializationSchemaWrapper[String](new SimpleStringSchema()), prop, FlinkKafkaProducer.Semantic.EXACTLY_ONCE)
		socketStream.addSink(kafkaSink)
		env.execute("FlinkKafkaSink")
	}
}
