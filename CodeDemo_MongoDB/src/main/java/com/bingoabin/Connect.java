package com.bingoabin;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/8/10 16:48
 * @Description: mongodb测试
 */
public class Connect {
	//连接mongo-无认证
	@Test
	public void testNoAuth(){
		try {
			//连接MongoDB服务端
			final MongoClient mongoClient = new MongoClient("localhost", 27017);
			//连接数据库
			final MongoDatabase database = mongoClient.getDatabase("kkb");
			System.out.println("mongodatabase is :" + database.getName());
			System.out.println("mongodatabase collections :" + database.getCollection("kaikeba"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//连接mongo-有认证
	@Test
	public void testHasAuth() {
		try {
			//连接mongodb的服务,如果是mongo集群的话按如下操作list
			final ServerAddress serverAddress = new ServerAddress("mongo", 27017);
			List<ServerAddress> list = new ArrayList<ServerAddress>();
			list.add(serverAddress);
			//开始通过用户名 密码进行认证
			final MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("admin", "mongo",
			                                                                                  "123456".toCharArray());
			List<MongoCredential> list1 = new ArrayList<MongoCredential>();
			list1.add(mongoCredential);

			//通过连接认证获取mongodb的连接
			final MongoClient mongoClient = new MongoClient(list, list1);
			//连接数据库
			final MongoDatabase mongo = mongoClient.getDatabase("kkb");
			System.out.println(mongo.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
