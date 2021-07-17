package com.bingoabin.datastream.source
//#将kafka作为flink的source来使用
//#实际工作当中一般都是将kafka作为flink的source来使用

//#创建kafka的topic
//#安装好kafka集群，并启动kafka集群，然后在node01执行以下命令创建kafka的topic为test
//kafka-topics.sh --create --partitions 3 --topic test --replication-factor 1 --zookeeper node01:2181,node02:2181,node03:2181
//代码实现

import java.util.Properties
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.util.serialization.SimpleStringSchema

/**
 * 将kafka作为flink的source来使用
 */
object KafkaSource {

	def main(args: Array[String]): Unit = {
		val env = StreamExecutionEnvironment.getExecutionEnvironment
		//**隐式转换
		import org.apache.flink.api.scala._
		//checkpoint**配置
		env.enableCheckpointing(100)
		env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
		env.getCheckpointConfig.setMinPauseBetweenCheckpoints(500)
		env.getCheckpointConfig.setCheckpointTimeout(60000)
		env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)
		env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)
		//设置statebackend
		env.setStateBackend(new RocksDBStateBackend("hdfs://node01:8020/flink_kafka_sink/checkpoints", true));
		val topic = "test"
		val prop = new Properties()
		prop.setProperty("bootstrap.servers", "node01:9092,node02:9092,node03:9092")
		prop.setProperty("group.id", "con1")
		prop.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		prop.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		val kafkaConsumer = new FlinkKafkaConsumer[String]("test", new SimpleStringSchema, prop)
		kafkaConsumer.setCommitOffsetsOnCheckpoints(true)
		val kafkaSource: DataStream[String] = env.addSource(kafkaConsumer)
		kafkaSource.print()
		env.execute()
	}
}

//#kafka生产数据
//#node01执行以下命令，通过shell命令行来生产数据到kafka当中去

// ##创建topic
// kafka-topics.sh --create --topic test --partitions 3 --replication-factor 2 --zookeeper node01:2181,node02:2181,node03:2181

//##发送数据
//kafka-console-producer.sh --broker-list node01:9092,node02:9092,node03:9092 --topic  test