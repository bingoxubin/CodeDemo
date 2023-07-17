package com.bingo.twophasecommit;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * @author bingoabin
 * @date 2023/7/14 16:31
 * @Description:
 */
public class FlinkKafkaToMysql {
	//topic
	private static final String topic_ExactlyOnce = "test";
	private static final String group_id = "TwoPhaseCommitConsumer";
	private static final String bootstrap_servers = "localhost:9092";
	private static final String statebackend_address = "file:///E:/60.test/checkpoint";

	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		//设置并行度,为了方便测试，查看消息的顺序，这里设置为1，可以更改为多并行度
		env.setParallelism(1);
		//checkpoint的设置
		//每隔10s进行启动一个检查点【设置checkpoint的周期】
		env.enableCheckpointing(10000);
		//设置模式为：exactly_one，仅一次语义
		env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
		//确保检查点之间有1s的时间间隔【checkpoint最小间隔】
		env.getCheckpointConfig().setMinPauseBetweenCheckpoints(1000);
		//检查点必须在10s之内完成，或者被丢弃【checkpoint超时时间】
		env.getCheckpointConfig().setCheckpointTimeout(10000);
		//同一时间只允许进行一次检查点
		env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
		//表示一旦Flink程序被cancel后，会保留checkpoint数据，以便根据实际需要恢复到指定的checkpoint
		env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
		//设置statebackend,将检查点保存在hdfs上面，默认保存在内存中。这里先保存到本地
		env.setStateBackend(new FsStateBackend(statebackend_address));

		//设置kafka消费参数
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, group_id);

		/*SimpleStringSchema可以获取到kafka消息，JSONKeyValueDeserializationSchema可以获取都消息的key,value，metadata:topic,partition，offset等信息*/
		FlinkKafkaConsumer<String> kafkaConsumer011 = new FlinkKafkaConsumer<>(topic_ExactlyOnce, new SimpleStringSchema(), properties);

		//加入kafka数据源
		DataStreamSource<String> streamSource = env.addSource(kafkaConsumer011);

		SingleOutputStreamOperator<Tuple2<String, Integer>> tupleStream = streamSource.map(str -> Tuple2.of(str, 1)).returns(Types.TUPLE(Types.STRING, Types.INT));

		tupleStream.print();
		//数据传输到下游
		tupleStream.addSink(new MysqlSinkPlus()).name("MySqlTwoPhaseCommitSink");
		//触发执行
		env.execute("Kafka2MysqlDemo");
	}
}

