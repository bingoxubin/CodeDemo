package com.bingoabin;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/8/10 20:45
 * @Description: 插入多条文档
 */
public class InsertDocuments {
	public static void main(String[] args) {
		//连接服务器
		final MongoClient mongoClient = new MongoClient("localhost", 27017);
		//连接数据库
		final MongoDatabase database = mongoClient.getDatabase("kkb");
		//连接集合
		final MongoCollection<Document> javaCollection = database.getCollection("javaCollection");
		//准备待插入的数据
		Document documen1 = new Document("_id", 1).append("title", "mongodb test collection")
		                                          .append("like", 30)
		                                          .append("url", "www.baidu.com");
		Document documen2 = new Document("_id", 2).append("title", "mongodb test collection")
		                                          .append("like", 31)
		                                          .append("url", "www.baidu.com");
		Document documen3 = new Document("_id", 3).append("title", "mongodb test collection")
		                                          .append("like", 32)
		                                          .append("url", "www.baidu.com");
		Document documen4 = new Document("_id", 4).append("title", "mongodb test collection")
		                                          .append("like", 33)
		                                          .append("url", "www.baidu.com");
		List<Document> list = new ArrayList<Document>();
		list.add(documen1);
		list.add(documen2);
		list.add(documen3);
		list.add(documen4);
		javaCollection.insertMany(list);
		javaCollection.find().forEach(printBlock);
	}

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(Document document) {
			System.out.println(document.toJson());
		}
	};
}
