package com.bingoabin;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author bingoabin
 * @date 2022/8/10 20:39
 * @Description: 插入文档
 */
public class InsertDocument {
	public static void main(String[] args) {
		try {
			//连接服务器
			final MongoClient mongoClient = new MongoClient("localhost", 27017);
			//连接数据库
			final MongoDatabase mongo = mongoClient.getDatabase("test0");
			for (String name : mongo.listCollectionNames()) {
				System.out.println("当前的集合 ：" + name);
			}
			//连接集合
			final MongoCollection<Document> javaCollection = mongo.getCollection("javaCollection");
			//准备数据
			Document documen =
					new Document("_id", 123).append("title", "mongodb test collection").append("like", 30).append("url"
							, "www.baidu.com");
			javaCollection.insertOne(documen);
			javaCollection.find().forEach(printBlock);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(Document document) {
			System.out.println(document.toJson());
		}
	};
}
