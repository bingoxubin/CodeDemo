package com.bingo.flink;

import org.apache.doris.flink.cfg.DorisStreamOptions;
import org.apache.doris.flink.datastream.DorisSourceFunction;
import org.apache.doris.flink.deserialization.SimpleListDeserializationSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Properties;
//读取数据 datastream方式
public class _02DataStreamSourceDemo {
	public static void main(String[] args) throws Exception {

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);

		Properties properties = new Properties();
		properties.put("fenodes", "bingo41:8030");
		properties.put("username", "test");
		properties.put("password", "test");
		properties.put("table.identifier", "test_db.table1");

		env.addSource(new DorisSourceFunction(
				              new DorisStreamOptions(properties),
				              new SimpleListDeserializationSchema()
		              )
		             ).print();

		env.execute();

	}
}
