package com.bingoabin;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author bingoabin
 * @date 2022/8/10 20:47
 * @Description: 更新文档
 */
public class UpdateDocument {
	public static void main(String[] args) {
		try{
			//连接mongodb的服务器
			final MongoClient mongoClient = new MongoClient("mongo", 27017);
			//连接mongodb的数据库
			final MongoDatabase mongo = mongoClient.getDatabase("kkb");
			//获取集合
			final MongoCollection<Document> javaCollection = mongo.getCollection("javaCollection");
			//更新文档
			javaCollection.updateOne(eq("_id" , 123) , new Document("$set" , new Document("title" , "update mongodb")));
			javaCollection.find().forEach(printBlock);
		}catch(Exception e){

		}

	}
	static Block<Document> printBlock = new Block<Document>() {
		public void apply(Document document) {
			System.out.println(document.toJson());
		}
	};
}
