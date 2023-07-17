package com.bingo.flink.stream;

import com.streamxhub.streamx.flink.core.StreamEnvConfig;
import com.streamxhub.streamx.flink.core.java.source.KafkaSource;
import com.streamxhub.streamx.flink.core.scala.StreamingContext;
import com.streamxhub.streamx.flink.core.scala.source.KafkaRecord;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

/**
 * @author bingoabin
 * @date 2023/7/16 10:41
 * @Description:
 */
public class StreamXKafkaDemo {
	public static void main(String[] args) {
		// FlinkAPI写法
		// StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		// 配置
		StreamEnvConfig javaConfig = new StreamEnvConfig(args, null);
		// 创建 StreamingContext 对象, 是一个核心类
		StreamingContext ctx = new StreamingContext(javaConfig);
		// 消费 kafka 数据
		DataStream<String> source = new KafkaSource<String>(ctx).getDataStream().map(new MapFunction<KafkaRecord<String>, String>() {
			@Override
			public String map(KafkaRecord<String> value) throws Exception {
				return value.value();
			}
		});

		source.print();
		// 启动任务
		ctx.start();
	}

	//注意：此处application.yml中应该用如下配置：
	//# kafka source 配置
	// kafka.source:
	//     bootstrap.servers: hadoop11:9092,hadoop12:9092,hadoop13:9092
	//     topic: s1
	//     group.id: bingo
	//     auto.offset.reset: latest
}
