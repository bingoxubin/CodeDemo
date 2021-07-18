package com.bingoabin.consumer;

/**
 * @author xubin34
 * @date 2021/7/19 1:56 上午
 */
//手动提交偏移量代码开发

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

//todo:需求：开发kafka消费者代码（手动提交偏移量）
public class KafkaConsumerControllerOffset {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
		props.put("group.id", "controllerOffset");
		//关闭自动提交，改为手动提交偏移量
		props.put("enable.auto.commit", "false");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		//指定消费者要消费的topic
		consumer.subscribe(Arrays.asList("test"));

		//定义一个数字，表示消息达到多少后手动提交偏移量
		final int minBatchSize = 20;

		//定义一个数组，缓冲一批数据
		List<ConsumerRecord<String, String>> buffer = new ArrayList<ConsumerRecord<String, String>>();
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				buffer.add(record);
			}
			if (buffer.size() >= minBatchSize) {
				//insertIntoDb(buffer);  拿到数据之后，进行消费
				System.out.println("缓冲区的数据条数：" + buffer.size());
				System.out.println("我已经处理完这一批数据了...");
				consumer.commitSync();
				buffer.clear();
			}
		}
	}
}
