package com.bingo;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class ElasticTest {
	private TransportClient client;

	@BeforeEach
	public void test1() throws UnknownHostException {
		Settings settings = Settings.builder().put("cluster.name", "myes").build();
		client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName("node01"), 9300)).addTransportAddress(new TransportAddress(InetAddress.getByName("node02"), 9300));
	}

	@Test
	public void index2() throws Exception {
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("name", "zhangsan");
		jsonMap.put("sex", "1");
		jsonMap.put("age", "18");
		jsonMap.put("address", "bj");
		IndexResponse indexResponse = client.prepareIndex("myindex1", "article", "2").setSource(jsonMap).get();
		client.close();
	}
}
