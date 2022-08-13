package com.bingoabin;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

/**
 * @author bingoabin
 * @date 2022/8/10 20:48
 * @Description: 更新多条文档
 */
public class UpdateDocuments {
	public static void main(String[] args) {
		//连接mongodb服务端
		final MongoClient mongoClient = new MongoClient("mongo", 27017);
		//连接数据库
		final MongoDatabase mongo = mongoClient.getDatabase("kkb");
		//连接集合
		final MongoCollection<Document> javaCollection = mongo.getCollection("javaCollection");
		javaCollection.updateMany(Filters.eq("like" , 30) , new Document("$set" , new Document("url" , "www.google.com")));
		javaCollection.find().forEach(printBlock);
	}


	static Block<Document> printBlock = new Block<Document>() {
		public void apply(Document document) {
			System.out.println(document.toJson());
		}
	};
}
