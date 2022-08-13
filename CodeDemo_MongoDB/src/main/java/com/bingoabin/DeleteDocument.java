package com.bingoabin;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author bingoabin
 * @date 2022/8/10 20:48
 * @Description: 删除单条文档
 */
public class DeleteDocument {
	public static void main(String args[]) {
		try {
			// 连接mongodb服务器
			MongoClient mongoClient = new MongoClient("mongo", 27017);
			// 连接mongodb数据库
			MongoDatabase database = mongoClient.getDatabase("kkb");
			// 获取集合
			MongoCollection<Document> collection = database.getCollection("javaCollection");
			// 删除文档
			collection.deleteOne(eq("_id", 123));
			collection.find().forEach(printBlock);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(final Document document) {
			System.out.println(document.toJson());
		}
	};
}
