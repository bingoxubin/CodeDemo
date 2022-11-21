package com.bingoabin;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

/**
 * TODO
 *
 * @author cjp
 * @version 1.0
 */
public class TestKafkaProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "hadoop1:9092,hadoop2:9092,hadoop3:9092");
        props.put("acks", "-1");
        props.put("batch.size", "1048576");
        props.put("linger.ms", "5");
        props.put("compression.type", "snappy");
        props.put("buffer.memory", "33554432");
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            JSONObject model = new JSONObject();
            model.put("userid", i);
            model.put("username", "name" + i);
            model.put("age", 18);
            model.put("partition", random.nextInt(100));
            producer.send(new ProducerRecord<String, String>("hudi_test", model.toJSONString()));
        }
        producer.flush();
        producer.close();

    }
}
