package com.bingoabin.stream;

/**
 * @author bingoabin
 * @date 2021/7/5 16:46
 */

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

// java代码开发实时统计每隔1秒统计最近2秒单词出现的次数
public class FlinkStreamJava {
	public static void main(String[] args) throws Exception {
		//步骤一：获取流式处理环境
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		//步骤二：获取socket数据
		DataStreamSource<String> sourceDstream = env.socketTextStream("localhost", 9999);
		//步骤三：对数据进行处理
		DataStream<WordCount> wordAndOneStream = sourceDstream.flatMap(new FlatMapFunction<String, WordCount>() {
			public void flatMap(String line, Collector<WordCount> collector) throws Exception {
				String[] words = line.split(" ");
				for (String word : words) {
					collector.collect(new WordCount(word, 1L));
				}
			}
		});
		DataStream<WordCount> resultStream = wordAndOneStream
				.keyBy("word")  //按照单词分组
				.timeWindow(Time.seconds(2), Time.seconds(1)) //每隔1s统计2s的数据
				.sum("count");   //按照count字段累加结果
		//步骤四：结果打印
		resultStream.print();
		//步骤五：任务启动
		env.execute("WindowWordCountJava");
	}
}

//2.发送socket数据
//在node01上执行命令，发送数据
//linux:  nc -lk 9999
//windows:  nc -l -p 9999
