package com.bingoabin.wc;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author bingoabin
 * @date 2023/7/7 16:59
 * @Description:
 */
public class Flink_WC {
	public static void main(String[] args) throws Exception {
		//创建流处理环境
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		//StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
		//从指定端口读取数据
		DataStreamSource<String> dataStreamSource = env.socketTextStream("bingo42", 9999);

		//对数据进行结构转换 分组  求和
		SingleOutputStreamOperator<Tuple2<String, Long>> resDS = dataStreamSource.flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
			@Override
			public void flatMap(String line, Collector<Tuple2<String, Long>> out) throws Exception {
				String[] words = line.split(" ");
				for (String word : words) {
					out.collect(Tuple2.of(word, 1L));
				}
			}
		}).keyBy(t -> t.f0).sum(1);

		resDS.print();
		env.execute();
	}
}
