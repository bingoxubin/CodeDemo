package com.bingo.flink.stream;

import com.bingo.flink.pojo.WaterSensor;
import com.streamxhub.streamx.flink.core.StreamEnvConfig;
import com.streamxhub.streamx.flink.core.java.function.SQLFromFunction;
import com.streamxhub.streamx.flink.core.java.sink.JdbcSink;
import com.streamxhub.streamx.flink.core.java.source.KafkaSource;
import com.streamxhub.streamx.flink.core.scala.StreamingContext;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;

/**
 * @author bingoabin
 * @date 2023/7/16 11:29
 * @Description:
 */
public class StreamXMySQLSinkDemo {
	public static void main(String[] args) {
		StreamEnvConfig javaConfig = new StreamEnvConfig(args, null);
		// 创建 StreamingContext 对象, 是一个核心类
		StreamingContext ctx = new StreamingContext(javaConfig);

		SingleOutputStreamOperator<WaterSensor> source = new KafkaSource<String>(ctx)
				.alias("kafka1")
				.topic("s1")
				.getDataStream()
				.map(record -> {
					String[] data = record.value().split(",");
					return new WaterSensor(data[0], Long.valueOf(data[1]), Integer.valueOf(data[2]));
				});

		new JdbcSink<WaterSensor>(ctx).sql(new SQLFromFunction<WaterSensor>() {
			// 抽取sql语句
			@Override
			public String from(WaterSensor ws) {
				return String.format("insert into sensor(id, ts, vc)values('%s', %d, %d)", ws.getId(), ws.getTs(), ws.getVc());
			}
		}).sink(source);

		// 启动任务
		ctx.start();
	}

	//注意：此处application.yml中应该用如下配置：
	//kafka.source:
	//   kafka1:
	//     # 配置第一个Kafka集群
	//     bootstrap.servers: hadoop11:9092,hadoop12:9092,hadoop13:9092
	//     topic: s1
	//     group.id: bingo1
	//     auto.offset.reset: latest
	//     enable.auto.commit: true
	//   kafka2:
	//     # 配置第二个kafka集群
	//     bootstrap.servers: hadoop11:9092,hadoop12:9092,hadoop13:9092
	//     topic: s2
	//     group.id: bingo2
	//     auto.offset.reset: latest
	//     enable.auto.commit: true
}
