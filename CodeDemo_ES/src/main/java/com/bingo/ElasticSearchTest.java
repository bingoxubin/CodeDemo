package com.bingo;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class ElasticSearchTest {
	//添加索引文件操作
	//1、创建Client
	private TransportClient client;

	@BeforeEach
	public void test1() throws UnknownHostException {
		Settings settings = Settings.builder().put("cluster.name", "myes").build();
		client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName("node01"), 9300)).addTransportAddress(new TransportAddress(InetAddress.getByName("node02"), 9300));
	}

	//2、自己拼装json创建索引保存到myindex1索引库下面的article当中去

	/**
	 * 插入json格式的索引数据
	 */
	@Test
	public void createIndex() {
		String json = "{" +
				"\"user\":\"kimchy\"," +
				"\"postDate\":\"2013-01-30\"," +
				"\"message\":\"travelying out Elasticsearch\"" +
				"}";
		IndexResponse indexResponse = client.prepareIndex("myindex1", "article", "1").setSource(json, XContentType.JSON).get();
		client.close();
	}

	//3、使用map创建索引
	@Test
	public void index2() throws Exception {
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("name", "zhangsan");
		jsonMap.put("sex", "1");
		jsonMap.put("age", "18");
		jsonMap.put("address", "bj");
		IndexResponse indexResponse = client.prepareIndex("myindex1", "article", "2")
		                                    .setSource(jsonMap)
		                                    .get();
		client.close();
	}

	//4、XcontentBuilder实现创建索引

	/**
	 * 通过XContentBuilder来实现索引的创建
	 *
	 * @throws IOException
	 */
	@Test
	public void index3() throws IOException {
		IndexResponse indexResponse = client.prepareIndex("myindex1", "article", "3")
		                                    .setSource(new XContentFactory().jsonBuilder()
		                                                                    .startObject()
		                                                                    .field("name", "lisi")
		                                                                    .field("age", "18")
		                                                                    .field("sex", "0")
		                                                                    .field("address", "bj")
		                                                                    .endObject())
		                                    .get();
		client.close();
	}
}