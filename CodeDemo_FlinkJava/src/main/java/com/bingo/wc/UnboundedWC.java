package com.bingo.wc;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author bingoabin
 * @date 2023/7/16 0:55
 * @Description:
 */
public class UnboundedWC {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);

		env.socketTextStream("hadoop11", 7777)
		   .flatMap(new FlatMapFunction<String, String>() {
			   @Override
			   public void flatMap(String line, Collector<String> out) {
				   for (String word : line.split(" ")) {
					   out.collect(word);
				   }
			   }
		   })
		   .map(new MapFunction<String, Tuple2<String, Long>>() {
			   @Override
			   public Tuple2<String, Long> map(String word) {
				   return Tuple2.of(word, 1L);
			   }
		   })
		   .keyBy(new KeySelector<Tuple2<String, Long>, String>() {
			   @Override
			   public String getKey(Tuple2<String, Long> t) {
				   return t.f0; // t._1
			   }
		   })
		   .sum(1)
		   .print();
		env.execute();

	}
}
