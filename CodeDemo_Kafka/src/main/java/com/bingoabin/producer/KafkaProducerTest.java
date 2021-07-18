package com.bingoabin.producer;

/**
 * @author xubin34
 * @date 2021/7/19 1:54 上午
 */
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
public class KafkaProducerTest {
	/**
	 * 通过javaAPI实现向kafka当中生产数据
	 * @param args
	 */
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
		//消息的确认机制
		props.put("acks", "all");
		props.put("retries", 0);
		//缓冲区的大小  //默认32M
		props.put("buffer.memory", 33554432);
		//批处理数据的大小，每次写入多少数据到topic   //默认16KB
		props.put("batch.size", 16384);
		//可以延长多久发送数据   //默认为0 表示不等待 ，立即发送
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		//指定数据序列化和反序列化
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String, String> producer = new KafkaProducer<String,String>(props);
		for(int i =0;i<100;i++){
			//既没有指定分区号，也没有数据的key，直接使用轮序的方式将数据发送到各个分区里面去
			ProducerRecord record = new ProducerRecord("test", "helloworld" + i);
			producer.send(record);
		}
		//关闭消息发送客户端
		producer.close();
	}
}
